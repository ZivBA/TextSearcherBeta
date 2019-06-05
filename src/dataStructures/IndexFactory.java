package dataStructures;

import dataStructures.dictionary.DictionaryIndexer;
import dataStructures.naive.NaiveIndexer;
import dataStructures.naive.NaiveIndexerRK;
import processing.textStructure.Corpus;

public class IndexFactory {
	public static Aindexer createIndexerByName(String name, Corpus corpus){
		if(name == null){
			throw new RuntimeException("no indexer given");
		}
		final Aindexer.IndexTypes forSwitch = Aindexer.IndexTypes.valueOf(name);
		switch (forSwitch){
			case NAIVE_RK:
				return new NaiveIndexerRK(corpus);
			case NAIVE:
			return new NaiveIndexer(corpus);
			case DICT:
				return new DictionaryIndexer(corpus);
			default:
				throw new RuntimeException("invalid indexer name");
		}

	}
}
