package rules;

import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;

import textStructure.Block;
import textStructure.QueryResult;


public interface IparsingRule {
	int MAXLINELENGTH = 256;
	int getWordDistance(QueryResult first, QueryResult second, String[] queryWords);
	Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos);
	List<Block> parseFile(RandomAccessFile inputFile);

}
