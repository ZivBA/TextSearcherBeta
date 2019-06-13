package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class STtvSeriesParsingRule extends ScriptParsingRule implements IparsingRule {
	static final private String START_BLOCK_REGEX = "^\\s([0-9]+)(.*)";
	static final private String CHARACHTOR_NAMES = "^[^\\S\\r\\n]+([A-Z]+)[^\\S\\r\\n]*\\n";
	static final private Pattern charachterPattern = Pattern.compile(CHARACHTOR_NAMES);

	public STtvSeriesParsingRule() {
    }


    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
        //TODO implement regex parsing using the following processing.parsingRules:
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
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        // this should actually be a comparator for wordResults, where two word results are considered equal if they come from the same block
        // if they come from different blocks, then either both blocks contain all the query words, in which case the order is according to scene number
        // otherwise, whichever block has more queryWords gets a higher score (is grater than the other)
        return -1;
    }

	@Override
	protected String getBlockStartRegex() {
		return START_BLOCK_REGEX;
	}

	@Override
	protected String getCharactersRegex() {
		return CHARACHTOR_NAMES;
	}



}
