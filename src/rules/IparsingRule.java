package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface IparsingRule {
    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException;
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords);


}
