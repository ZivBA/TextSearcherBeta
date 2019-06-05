package processing.parsingRules;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

/**
 * An interface describing the contract any parser should maintain, as well as possible default methods.
 */
public interface IparsingRule {
	int MAXLINELENGTH = 256;

	/**
	 * A distance meaasure between WordResult objects. Used to sort results by some confidence measure
	 * @param first     First WordResult object
	 * @param second    Second WordResult object
	 * @param queryWords    The actual query sent to the indexer
	 * @return  An integer representing some distance measure between the results.
	 */
	int getWordDistance(WordResult first, WordResult second, String[] queryWords);

	/**
	 * A parser for a single block of text
	 * @param inputFile A RandomAccessFile from which we are reading
	 * @param startPos  The starting position of the block within the file
	 * @param endPos    The end position of the block within the file
	 * @return          A Block Object.
	 */
	Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos);

	/**
	 * A parser for the entire file.
	 * @param inputFile The RandomAccessFile from which we are reading.
	 * @return  A list of Block objects describing the file.
	 */
	List<Block> parseFile(RandomAccessFile inputFile);

	/**
	 * Utility method to create a matcher regex for an arbitrary list of words.
	 * @param qWords    an array of Strings representing the query words.
	 * @return  A regex String to be compiled into a pattern.
	 */
	default String getMatcherRegex(String[] qWords) {
		StringBuilder matchAllWordsRegex = new StringBuilder();
		for (String word : qWords){
			matchAllWordsRegex.append("((").append(word).append(")).*?");
		}
		matchAllWordsRegex.append("\n");
		return matchAllWordsRegex.toString();
	}

	/**
	 * Print a WordResult object according to the parsing rules.
	 * @param wordResult    The WordResult to be printed
	 * @throws IOException  If the RAF misbehaves.
	 */
	void printResult(WordResult wordResult) throws IOException;
}
