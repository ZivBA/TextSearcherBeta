package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public abstract class AparsingRule implements Iterator<Block> {
    protected static final int MAX_LINE_LENGTH = 256;

    public abstract int getWordDistance(WordResult first, WordResult second, String[] queryWords);
    protected long nextIndex;
    protected Block nextBlock;
    protected RandomAccessFile inputFile;
    protected String fileAsString;
    public AparsingRule(RandomAccessFile file){
        inputFile = file;
        nextIndex = 0;
        fileAsString = readFileToString();
    }

    private String readFileToString() {
        try{
            byte b[] = new byte[(int) inputFile.length()];
            inputFile.readFully(b);
            return new String(b);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    protected abstract BlockLocation getNewBlockLocation(long startIndex);

    @Override
    public boolean hasNext() {
        BlockLocation loc = getNewBlockLocation(nextIndex);
        return loc != null;
    }

    protected class BlockLocation{
        private long start;
        private long end;
        public BlockLocation(long start, long end){
            this.start = start;
            this.end = end;
        }

        public long getStart(){
            return start;
        }

        public long getEnd(){
            return end;
        }
    }
}
