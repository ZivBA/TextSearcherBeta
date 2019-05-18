package indexing;

import indexing.dataStructures.dictionary.DictionaryIndexer;
import indexing.dataStructures.naive.NaiveIndexer;
import rules.IparsingRule;
import textStructure.Corpus;

public class IndexFactory {
	public static Aindexer createIndexerByName(String name, Corpus corpus,IparsingRule parsingRule){
		if(name == null){
			throw new RuntimeException("no indexer given");
		}
		switch (name){
			case NaiveIndexer.TYPE_NAME:
				return new NaiveIndexer(corpus,parsingRule);
			case DictionaryIndexer.TYPE_NAME:
				return new DictionaryIndexer(corpus,parsingRule);
			default:
				throw new RuntimeException("invalid indexer name");
		}

	}
}
