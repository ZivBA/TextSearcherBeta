package indexing;

import indexing.dataStructures.dictionary.DictionaryIndexer;
import indexing.dataStructures.naive.NaiveIndexer;
import textStructure.Corpus;

public class IndexFactory {
    public static Aindexer createIndexerByName(String name, Corpus corpus){
        if(name == null){
            throw new RuntimeException("no indexer given");
        }
        switch (name){
            case "naive":
                return new NaiveIndexer(corpus);
            case "dictionary":
                return new DictionaryIndexer(corpus);
            default:
                throw new RuntimeException("invalid indexer name");
        }

    }
}
