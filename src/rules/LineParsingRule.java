//package rules;
//
//import textStructure.Block;
//import textStructure.LinesBlock;
//import textStructure.QueryResult;
//
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.util.Iterator;
//
//public abstract class LineParsingRule implements IparsingRule {
//    protected static final int MAX_LINE_LENGTH = 256;
//    protected String lines[];
//    long nextIndex;
//    private Block nextBlock;
//    int currLine;
//    protected RandomAccessFile inputFile;
//    String fileAsString;
//
//    public LineParsingRule(RandomAccessFile file){
//       this.inputFile = file;
//    }
//
//    protected abstract String getSplitRegex();
//
//
//
//    String readFileToString() {
//        try{
//            byte b[] = new byte[(int) inputFile.length()];
//            inputFile.readFully(b);
//            return new String(b);
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Block next() {
//        LinesBlock b = getNewBlockLocation(currLine);
//
//        nextBlock = new Block(inputFile,b.getStartIndex(),b.getEndIndex()-1);
//        nextIndex = b.getEndIndex();
//        currLine = b.getLastLine();
//        return nextBlock;
//    }
//
//    @Override
//    public boolean hasNext() {
//        Block loc = getNewBlockLocation(currLine);
//        return loc != null;
//    }
//
//    private LinesBlock getNewBlockLocation(int lineStartIndex) {
//    	LinesBlock b = new LinesBlock(inputFile);
//    	lineStartIndex = getNextBlockLineStartIndex(lineStartIndex);
//
//        long start = -1;
//        try {
//        	start = fileAsString.indexOf(lines[lineStartIndex],(int)nextIndex);
//        	b.setStart(start);
//            setBlockEndIndex(lineStartIndex, b);
//
//        }catch(ArrayIndexOutOfBoundsException e) {
//        	return null;
//        }
//
//        return b;
//    }
//
//    protected void setBlockEndIndex(int lineStartIndex, LinesBlock b) {
//    	int blockLineEnd = getNextBlockLineStartIndex(lineStartIndex + 1);
//		long end = blockLineEnd < lines.length ?
//        		fileAsString.indexOf(lines[blockLineEnd],(int)nextIndex + lines[lineStartIndex].length()) : fileAsString.length();
//
//		b.setLineEnd(blockLineEnd).setEnd(end);
//
//	}
//
//	protected int getNextBlockLineStartIndex(int fromLine) {
//
//    	String line;
//        try{
//            line = lines[fromLine];
//
//            while(!isStartOfBlock(line)){
//            	fromLine++;
//                line = lines[fromLine];
//            }
//        }catch (ArrayIndexOutOfBoundsException ignored){
//
//        }
//        return fromLine;
//    }
//
//	protected abstract boolean isStartOfBlock(String line);
//
//
//
//
//}
