package indexing;

import indexing.dataStructures.IdataStructure;
import indexing.dataStructures.naiveSearch.NaiveSearch;
import rules.IparsingRule;
import rules.SimpleParsingRule;
import textStructure.Corpus;
import textStructure.Entry;

public abstract class Aindexer {
    static final String DICT = "dict";
    static final String NAIVE = "naive";
    static final String RK = "naiveRK";

    String dataStructType;
    IparsingRule parseRule;
    IdataStructure dataStruct;
    Corpus origin;

    Aindexer() {
    }

    public abstract Entry indexEntry(Entry inputEntry);
    public void setOrigin(Corpus origin){
        this.origin = origin;
    }

    Aindexer(Corpus origin){
        this.origin = origin;
        this.dataStructType = NAIVE;
        this.parseRule = new SimpleParsingRule();
        this.dataStruct = new NaiveSearch(this.origin);
    }


}
