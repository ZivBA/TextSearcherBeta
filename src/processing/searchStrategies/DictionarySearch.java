package processing.searchStrategies;

import indexing.textStructure.Block;
import indexing.textStructure.ComplexQueryResult;
import indexing.textStructure.Word;
import indexing.textStructure.WordResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DictionarySearch implements IQuerySearch {

	//	List<BlockDict> dicts;
//	public DictionarySearch(List<BlockDict> dicts) {
	//		this.dicts = dicts;
//	}

	private HashMap<Integer, List<Word>> dict;

	public DictionarySearch(HashMap<Integer, List<Word>> dict) {
		this.dict = dict;
	}

	@Override
	public List<? extends WordResult> search(String query) {
		PriorityQueue<ComplexQueryResult> results = new PriorityQueue<>();
		String[] words = query.split("\\s+");
			List<Word> firstWords = dict.get(stemmer.stem(words[0]).hashCode());

			for(Word word : firstWords) {
				long[] locs = new long[words.length];
				locs[0] = word.getEntryIndex();
				boolean success = setWordLocsStartingFrom(locs,words,1, word.getSrcBlk());
				if(success) {
					results.add(new ComplexQueryResult(words,word.getSrcBlk(),locs));
				}
		}
		
		

		return new ArrayList<>(results);
	}

	private boolean setWordLocsStartingFrom(long[] locs, String[] words, int nextWordIndex, Block srcBlk) {
		if(nextWordIndex==words.length) {
			return true;
		}
		String stemmed = stemmer.stem(words[nextWordIndex]);
		List<Word> newWords = dict.get(stemmed.hashCode());
		for(Word word : newWords) {
			long loc = word.getEntryIndex();
			if(loc>locs[nextWordIndex-1] && word.getSrcBlk()==srcBlk) {
				locs[nextWordIndex] = loc;
				return setWordLocsStartingFrom(locs,words,nextWordIndex + 1, word.getSrcBlk());
			}
		}
		return false;
	}

}
