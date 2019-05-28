package search;

import java.util.Collection;
import java.util.List;

import textStructure.QueryResult;
import utils.Stemmer;

public interface IQuerySearch {
    final Stemmer stemmer = new Stemmer();
	public Collection<QueryResult> search(String query);
}
