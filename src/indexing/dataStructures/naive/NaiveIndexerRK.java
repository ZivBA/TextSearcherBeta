package indexing.dataStructures.naive;

import indexing.textStructure.Corpus;

public class NaiveIndexerRK extends NaiveIndexer {

    public static final String TYPE_NAME = "naive_indexer_RK";

    public NaiveIndexerRK(Corpus corpus) {
        super(corpus, true);
    }

}
