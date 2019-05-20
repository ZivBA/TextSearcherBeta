package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.RandomAccessFile;
import java.util.Iterator;

public abstract class AparsingRule implements Iterator<Block> {
    protected static final int MAX_LINE_LENGTH = 256;

    public abstract int getWordDistance(WordResult first, WordResult second, String[] queryWords);
    protected int nextIndex;
    protected Block nextBlock;
    protected RandomAccessFile inputFile;
    public AparsingRule(RandomAccessFile file){
        inputFile = file;
        nextIndex = 0;
        nextBlock = this.next();
    }

}
