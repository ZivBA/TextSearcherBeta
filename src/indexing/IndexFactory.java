package indexing;

import indexing.dataStructures.dictionary.DictionaryIndexer;
import indexing.dataStructures.naive.NaiveIndexer;
import rules.AparsingRule;
import textStructure.Corpus;

public class IndexFactory {
	public static Aindexer createIndexerByName(String name, Corpus corpus){
		if(name == null){
			throw new RuntimeException("no indexer given");
		}
		switch (name){
			case NaiveIndexer.TYPE_NAME:
				return new NaiveIndexer(corpus);
			case DictionaryIndexer.TYPE_NAME:
				return new DictionaryIndexer(corpus);
			default:
				throw new RuntimeException("invalid indexer name");
		}

	}
}
