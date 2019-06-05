package processing.searchStrategies;

import processing.textStructure.Word;
import processing.textStructure.WordResult;

import java.util.*;

public class DictionarySearch implements IsearchStrategy {

	private HashMap<Integer, List<Word>> dict;

	public DictionarySearch(HashMap<Integer, List<Word>> dict) {

		this.dict = dict;
	}

	/**
	 * Search the dictionary generated by the indexer for query results
	 * @param query The query string to search for.
	 * @return  A list of WordResults or any extension of them.
	 */
	@Override
	public List<? extends WordResult> search(String query) {
		//TODO implement me!!!
	}



}
