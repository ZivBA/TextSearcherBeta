package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class STtvSeriesParsingRule implements IparsingRule {

	private final RandomAccessFile inputFile;

	public STtvSeriesParsingRule(RandomAccessFile randomAccessFile) {

    }


    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {

    }

    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        // this should actually be a comparator for wordResults, where two word results are considered equal if they come from the same block
        // if they come from different blocks, then either both blocks contain all the query words, in which case the order is according to scene number
        // otherwise, whichever block has more queryWords gets a higher score (is grater than the other)
        return -1;
    }

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {
		return null;
	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {
		return null;
	}

	@Override
	public String getMatcherRegex(String[] qWords) {
		return null;
	}

	@Override
	public void printResult(WordResult wordResult) throws IOException {

	}

}
