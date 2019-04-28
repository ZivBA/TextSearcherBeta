package indexing;

import indexing.dataStructures.dictionarySearch.DictionaryIndexer;
import indexing.dataStructures.naiveSearch.NaiveSearch;
import rules.IparsingRule;
import textStructure.Corpus;
import textStructure.Entry;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class Indexer extends Aindexer {

    public Indexer(String dataStrucType, IparsingRule parseRule){
        this.parseRule = parseRule;
        this.dataStructType = dataStrucType;
        switch (dataStrucType){
            case DICT:
                this.dataStruct = new DictionaryIndexer();
                break;
            case NAIVE:
                this.dataStruct = new NaiveSearch();

        }
    }

    public Collection<Entry> indexCorpus(Corpus origin){
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (Entry file : origin){
            entries.add(this.indexEntry(file));
        }
        return entries;
    }

    public Entry indexEntry(Entry inputEntry){
        this.dataStruct.indexEntry(inputEntry.getFile());
        return new Entry(this.dataStruct, this.parseRule);
    }

    private void writeToFile(){
        try {
            String dictFile = this.origin.getPath() + this.dataStructType+"_index.cache";
            String hashCode = this.origin.getChecksum();
            FileOutputStream fileOut = new FileOutputStream(dictFile);

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(hashCode);

            objectOut.writeObject(this.dataStruct);

            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
    }

}
