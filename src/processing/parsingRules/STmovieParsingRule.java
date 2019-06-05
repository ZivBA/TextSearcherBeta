package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class STmovieParsingRule implements IparsingRule {

	private final RandomAccessFile inputFile;

	public STmovieParsingRule(RandomAccessFile file) throws IOException {

	}



	@Override
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {

	}

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {

	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {

	}

	@Override
	public void printResult(WordResult wordResult) throws IOException {

	}
}