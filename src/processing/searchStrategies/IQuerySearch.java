package processing.searchStrategies;

import indexing.textStructure.WordResult;
import processing.utils.Stemmer;

import java.util.List;

public interface IQuerySearch {
    final Stemmer stemmer = new Stemmer();
	public List<? extends WordResult> search(String query);
}
