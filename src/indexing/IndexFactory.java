package indexing;

import indexing.dataStructures.dictionary.DictionaryIndexer;
import indexing.dataStructures.naive.NaiveIndexer;
import indexing.dataStructures.naive.NaiveIndexerRK;
import textStructure.Corpus;

public class IndexFactory {
	public static Aindexer createIndexerByName(String name, Corpus corpus){
		if(name == null){
			throw new RuntimeException("no indexer given");
		}
		switch (name){
			case NaiveIndexerRK.TYPE_NAME:
				return new NaiveIndexerRK(corpus);
			case NaiveIndexer.TYPE_NAME:
			return new NaiveIndexer(corpus);
			case DictionaryIndexer.TYPE_NAME:
				return new DictionaryIndexer(corpus);
			default:
				throw new RuntimeException("invalid indexer name");
		}

	}
}
