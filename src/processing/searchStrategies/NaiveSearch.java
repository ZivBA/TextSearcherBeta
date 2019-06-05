package processing.searchStrategies;

import java.util.ArrayList;
import java.util.List;

import processing.textStructure.Block;
import processing.textStructure.Corpus;
import processing.textStructure.Entry;
import processing.textStructure.WordResult;

public class NaiveSearch implements IsearchStrategy {
	private Corpus origin;
	public NaiveSearch(Corpus origin) {
		this.origin = origin;
	}

	private void searchBlock(Block blk, List<WordResult> results, String query) {
		String toSearch = blk.toString();
		int lastIndex = 0;

		while(lastIndex != -1){

		    lastIndex = toSearch.indexOf(query,lastIndex);

		    if(lastIndex != -1){
		    	results.add(new WordResult(blk, new String[]{query},lastIndex));
		        lastIndex += query.length();
		    }
		}
	}

	@Override
	public List<WordResult> search(String query) {
		// TODO Auto-generated method stub
		List<WordResult> results = new ArrayList<>();
		for (Entry entry : this.origin){
			for (Block blk : entry){
				this.searchBlock(blk, results, query);
			}
		}
		return results;
	}

}
