package rules;

import java.util.Iterator;

import textStructure.Block;
import textStructure.QueryResult;


public interface IparsingRule extends Iterator<Block> {
	public int getWordDistance(QueryResult first, QueryResult second, String[] queryWords);

}
