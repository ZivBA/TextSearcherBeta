package processing.searchStrategies;

import processing.textStructure.Block;
import processing.textStructure.Corpus;
import processing.textStructure.Entry;
import processing.textStructure.WordResult;

import java.util.ArrayList;
import java.util.List;

public class NaiveSearch implements IsearchStrategy {
	private Corpus origin;
	public NaiveSearch(Corpus origin) {
		this.origin = origin;
	}


	/**
	 * The main search method to comply with the IsearchStrategy interface
	 * @param query The query string to search for.
	 * @return  A list of wordResults
	 */
	@Override
	public List<WordResult> search(String query) {
		//TODO implement me like one of your french algorithms!
	}

}
