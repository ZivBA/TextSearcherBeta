package indexing;

import indexing.algorithms.IsearchAlgorithm;
import indexing.algorithms.dictionarySearch.DictionaryIndexer;
import rules.IparsingRule;
import textStructure.Corpus;
import textStructure.Entry;

import java.util.ArrayList;
import java.util.Collection;

public class Indexer implements Iindexing{

    private static final String DICT = "dict";

    private IsearchAlgorithm searchAlg;
    private Corpus origin;
    private IparsingRule parseRule;

    public Indexer(String indexAlg, IparsingRule parseRule){
        this.parseRule = parseRule;
        switch (indexAlg){
            case DICT:
                this.searchAlg = new DictionaryIndexer();
        }
    }

    public void setOrigin(Corpus origin){
        this.origin = origin;
    }

    public Collection<Entry> indexCorpus(Corpus origin){
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (String file : origin){
            entries.add(this.indexFile(file));
        }
        return entries;
    }

    public Entry indexFile(String inputFile){
        this.searchAlg.indexFile(inputFile);
        return new Entry(this.searchAlg, this.parseRule);
    }



}
