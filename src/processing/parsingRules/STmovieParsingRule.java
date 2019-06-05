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
		//TODO implement me!!!
	}



	@Override
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
		//TODO implement me!!!
	}

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {
		//TODO implement me!!!
	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {
		//TODO implement me!!!
	}

	@Override
	public void printResult(WordResult wordResult) throws IOException {
		//TODO implement me!!!
	}
}