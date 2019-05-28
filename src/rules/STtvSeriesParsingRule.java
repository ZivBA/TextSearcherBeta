package rules;

import textStructure.Block;
import textStructure.QueryResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class STtvSeriesParsingRule extends LineParsingRule {

    public STtvSeriesParsingRule(RandomAccessFile randomAccessFile) {
        super(randomAccessFile);
    }


    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
        //TODO implement regex parsing using the following rules:
        /*
            each scene starts with a line like "1    EXT. SPACE - STARSHIP (OPTICAL)" (no tabs)
            where the first number represent the scene number.
            within a scene there are lines like "	The U.S.S. Enterprise NCC 1701-D traveling at warp speed" (one tab)
            which are descriptions and are not interesting except for indexing words
            there are lines like: "					PICARD V.O." (five tabs)
            where there are only upper case letters, describing who is speaking and should be stored within the
            block MD as charachters within the scene.
            lines like "			... my crew we are short in" (three tabs)
            represent dialog.

            The indexer must store, efficiently, the characters' names and scene locations within the block MD for pretty printing the results.
            */
        return null;
    }

    @Override
    public int getWordDistance(QueryResult first, QueryResult second, String[] queryWords) {
        // this should actually be a comparator for wordResults, where two word results are considered equal if they come from the same block
        // if they come from different blocks, then either both blocks contain all the query words, in which case the order is according to scene number
        // otherwise, whichever block has more queryWords gets a higher score (is grater than the other)
        return -1;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Block next() {
        return null;
    }

	@Override
	protected String getSplitRegex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNextBlockLineStartIndex(int fromLine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean isStartOfBlock(String line) {
		// TODO Auto-generated method stub
		return false;
	}
}
