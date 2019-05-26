package rules;

import java.util.Iterator;

import textStructure.Block;
import textStructure.WordResult;


public interface IparsingRule extends Iterator<Block> {
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords);

}
