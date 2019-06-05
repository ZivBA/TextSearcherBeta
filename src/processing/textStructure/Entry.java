package processing.textStructure;

import processing.parsingRules.IparsingRule;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final IparsingRule parseRule;
    private List<Block> blockList;
    private String sourceFile;

    public Entry(String filePath, IparsingRule parseRule) {
        this.sourceFile = filePath;

        try {
            this.parseRule = parseRule;
            this.blockList = this.parseRule.parseFile(new RandomAccessFile(sourceFile, "r"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Iterator<Block> iterator() {
        return this.blockList.iterator();
    }

    public String getFilePath() {
        return this.sourceFile;
    }

    public byte[] getBytes() {
        return new byte[0];
    }
    
//    @Override
//    public String toString(){
//    	try {
//    		File file = new File("a.txt");
//        	FileInputStream fis = new FileInputStream(sourceFile);
//        	byte[] data = new byte[(int) file.length()];
//        	fis.read(data);
//        	fis.close();
//        	String str = new String(data, "UTF-8"); 
//        	return str;
//    	}catch(IOException e) {
//    		throw new RuntimeException(e);
//    	}
//    	
//    }
}
