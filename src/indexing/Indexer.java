package indexing;

import indexing.dataStructures.dictionarySearch.DictionaryIndexer;
import indexing.dataStructures.naiveSearch.NaiveIndexer;
import rules.IparsingRule;
import textStructure.Corpus;
import textStructure.Entry;

import java.util.ArrayList;
import java.util.Collection;

public class Indexer extends Aindexer {


    private Corpus origin;

    public Indexer(String dataStrucType, IparsingRule parseRule){
        this.parseRule = parseRule;
        switch (dataStrucType){
            case DICT:
                this.dataStruct = new DictionaryIndexer();
                break;
            case NAIVE:
                this.dataStruct = new NaiveIndexer();

        }
    }

    public void setOrigin(Corpus origin){
        this.origin = origin;
    }

    public Collection<Entry> indexCorpus(Corpus origin){
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (Entry file : origin){
            entries.add(this.indexEntry(file));
        }
        return entries;
    }

    public Entry indexEntry(Entry inputEntry){
        this.dataStruct.indexFile(inputEntry.getFile());
        return new Entry(this.dataStruct, this.parseRule);
    }



}
