package search;

import java.util.ArrayList;
import java.util.List;

import textStructure.Block;
import textStructure.Corpus;
import textStructure.Entry;
import textStructure.QueryResult;

public class NaiveSearch implements IQuerySearch {
	private Corpus origin;
	public NaiveSearch(Corpus origin) {
		this.origin = origin;
	}

	private void searchBlock(Block blk, List<QueryResult> results, String query) {
		String toSearch = blk.toString();
		int lastIndex = 0;

		while(lastIndex != -1){

		    lastIndex = toSearch.indexOf(query,lastIndex);

		    if(lastIndex != -1){
		    	results.add(new QueryResult(blk, query,lastIndex));
		        lastIndex += query.length();
		    }
		}
	}

	@Override
	public List<QueryResult> search(String query) {
		// TODO Auto-generated method stub
		List<QueryResult> results = new ArrayList<>();
		for (Entry entry : this.origin){
			for (Block blk : entry){
				this.searchBlock(blk, results, query);
			}
		}
		return results;
	}

}
