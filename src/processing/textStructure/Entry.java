package processing.textStructure;

import processing.parsingRules.IparsingRule;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class Entry implements Iterable<Block>, Serializable {
	public static final long serialVersionUID = 1L;
    private final IparsingRule parseRule;
    private List<Block> blockList;
    private String sourceFile;

    public Entry(String filePath, IparsingRule parseRule) {
        this.sourceFile = filePath;

        try {
            this.parseRule = parseRule;
            System.out.println(filePath);
            this.blockList = this.parseRule.parseFile(new RandomAccessFile(sourceFile, "r"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Iterator<Block> iterator() {
        return this.blockList.iterator();
    }

	void updateRAFs() {
		try {
			RandomAccessFile raf = new RandomAccessFile(new File(this.sourceFile),"r");
			for (Block blk : this.blockList){
				blk.inputFile = raf;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
