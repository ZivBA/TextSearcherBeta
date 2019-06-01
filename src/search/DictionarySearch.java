package search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import indexing.dataStructures.dictionary.BlockDict;
import textStructure.Block;
import textStructure.ComplexQueryResult;
import textStructure.QueryResult;

public class DictionarySearch implements IQuerySearch {
	List<BlockDict> dicts;
	
	public DictionarySearch(List<BlockDict> dicts) {
		this.dicts = dicts;
	}

	@Override
	public Collection<? extends QueryResult> search(String query) {
		PriorityQueue<ComplexQueryResult> results = new PriorityQueue<>();
		String[] words = query.split("\\s+");
		for(BlockDict dict: dicts) {
			List<Long> firstWordLocs = dict.getDict().computeIfAbsent(words[0].hashCode(), k -> new ArrayList<>());
			for(long pos: firstWordLocs) {
				int[] locs = new int[words.length];
				locs[0] = (int) pos;
				boolean success = setWordLocsStartingFrom(locs,words,1,dict);
				if(success) {
					results.add(new ComplexQueryResult(query,dict.geBlock(),locs));
				}
			}
		}
		
		

		return results;
	}

	private boolean setWordLocsStartingFrom(int[] locs, String[] words, int nextWordIndex, BlockDict dict) {
		if(nextWordIndex==words.length) {
			return true;
		}
		String stemmed = stemmer.stem(words[nextWordIndex]);
		List<Long> nextWordLocs = dict.getDict().computeIfAbsent(stemmed.hashCode(), k -> new ArrayList<>());
		for(long loc: nextWordLocs) {
			if(loc>locs[nextWordIndex-1]) {
				return setWordLocsStartingFrom(locs,words,nextWordIndex + 1,dict);
			}
		}
		return false;
	}

}
