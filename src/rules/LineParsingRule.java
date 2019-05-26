package rules;

import textStructure.Block;
import textStructure.LinesBlock;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public abstract class LineParsingRule implements IparsingRule {
    protected static final int MAX_LINE_LENGTH = 256;
    protected String lines[];
    protected long nextIndex;
    protected Block nextBlock;
    protected int currLine;
    protected RandomAccessFile inputFile;
    protected String fileAsString;
    
    public LineParsingRule(RandomAccessFile file){
        inputFile = file;
        nextIndex = 0;
        fileAsString = readFileToString();
        lines = fileAsString.split(getSplitRegex());
        System.out.print(lines.length);
        for(String line : lines) {
        	System.out.println(line);
        	System.out.println("___");
        }
        currLine = 0;
    }
    
    protected abstract String getSplitRegex();



    private String readFileToString() {
        try{
            byte b[] = new byte[(int) inputFile.length()];
            inputFile.readFully(b);
            return new String(b);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    
    @Override
    public Block next() {
        LinesBlock b = getNewBlockLocation(currLine);

        nextBlock = new Block(inputFile,b.getStartIndex(),b.getEndIndex()-1);
        nextIndex = b.getEndIndex();
        currLine = b.getLastLine();
        return nextBlock;
    }
    
    protected LinesBlock getNewBlockLocation(int lineStartIndex) {
    	LinesBlock b = new LinesBlock(inputFile);
    	lineStartIndex = getNextBlockLineStartIndex(lineStartIndex);
        
        long start = -1;
        try {
        	start = fileAsString.indexOf(lines[lineStartIndex],(int)nextIndex);
        	b.setStart(start);
            setBlockEndIndex(lineStartIndex, b);
            
        }catch(ArrayIndexOutOfBoundsException e) {
        	return null;
        }
        
        return b;
    }

    protected void setBlockEndIndex(int lineStartIndex, LinesBlock b) {
    	int blockLineEnd = getNextBlockLineStartIndex(lineStartIndex + 1);
		long end = blockLineEnd < lines.length ? 
        		fileAsString.indexOf(lines[blockLineEnd],(int)nextIndex + lines[lineStartIndex].length()) : fileAsString.length();
        		
		b.setLineEnd(blockLineEnd).setEnd(end);
		
	}

	protected int getNextBlockLineStartIndex(int fromLine) {
    	
    	String line;
        try{
            line = lines[fromLine];

            while(!isStartOfBlock(line)){
            	fromLine++;
                line = lines[fromLine];
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
        return fromLine;
    }
    
	protected abstract boolean isStartOfBlock(String line);


	@Override
    public boolean hasNext() {
        Block loc = getNewBlockLocation((int)currLine);
        return loc != null;
    }

}
