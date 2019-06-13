package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class STmovieParsingRule extends ScriptParsingRule implements IparsingRule {
	static final private String START_BLOCK_REGEX = "^\\s{5,15}([0-9]+)(.*)\\1";
	static final private String CHARACHTOR_NAMES = "^[^\\S\\r\\n]+([A-Z]+)[^\\S\\r\\n]*\\n";
	static final private Pattern charachterPattern = Pattern.compile(CHARACHTOR_NAMES);


	public STmovieParsingRule() throws IOException {
	}

//	public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
//		//TODO implement regex parsing using the following processing.parsingRules:
//        /*
//            each scene starts with a line like "               1   BLACK                                                        1"
//            where the first and last numbers represent the scene number.
//            within a scene there are lines like "                   Absolute quiet. SOUND bleeds in. Low level b.g."
//            which are descriptions and are not interesting except for indexing words
//            there are lines like: "                                           SAAVIK'S VOICE"
//            where there are only upper case letters, describing who is speaking and should be stored within the
//            block MD as charachters within the scene.
//            lines less indented but also only uppercase like "                   INT. ENTERPRISE BRIDGE"
//            are scene locations and should also be stored in MD as locations in scene
//            lines like "                             Leaving Section Fourteen for"
//            are less indented than scene description, and represent dialog.
//
//            The indexer must store, efficiently, the characters' names and scene locations within the block MD for pretty printing the results.
//
//         */
//		return null;
//
//	}

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