package processing.parsingRules;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import indexing.textStructure.Block;
import indexing.textStructure.WordResult;


public interface IparsingRule {
	int MAXLINELENGTH = 256;
	int getWordDistance(WordResult first, WordResult second, String[] queryWords);
	Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos);
	List<Block> parseFile(RandomAccessFile inputFile);

	default String getMatcherRegex(String[] qWords) {
		StringBuilder matchAllWordsRegex = new StringBuilder();
		for (String word : qWords){
			matchAllWordsRegex.append("((").append(word).append(")).*?");
		}
		matchAllWordsRegex.append("\n");
		return matchAllWordsRegex.toString();
	}

	void printResult(WordResult wordResult) throws IOException;
}
