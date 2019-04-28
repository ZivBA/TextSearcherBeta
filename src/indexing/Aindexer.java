package indexing;

import indexing.dataStructures.IdataStructure;
import indexing.dataStructures.naiveSearch.NaiveIndexer;
import rules.IparsingRule;
import rules.SimpleParsingRule;
import textStructure.Corpus;
import textStructure.Entry;

public abstract class Aindexer {
    static final String DICT = "dict";
    static final String NAIVE = "naive";

    String dataStructType;
    IparsingRule parseRule;
    IdataStructure dataStruct;
    Corpus origin;

    Aindexer() {
    }

    public abstract Entry indexEntry(Entry inputEntry);

    Aindexer(Corpus origin){
        this.origin = origin;
        this.dataStructType = NAIVE;
        this.parseRule = new SimpleParsingRule();
        this.dataStruct = new NaiveIndexer(this.origin);
    }

}
