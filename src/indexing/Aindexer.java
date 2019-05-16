package indexing;

import indexing.dataStructures.IdataStructure;
import rules.IparsingRule;
import textStructure.Corpus;
import textStructure.Entry;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Aindexer {
    static final String DICT = "dict";
    static final String NAIVE = "naive";
    static final String RK = "naiveRK";

    String dataStructType;
    IparsingRule parseRule;
    Corpus origin;

    public abstract Entry indexEntry(Entry inputEntry);
    public void setOrigin(Corpus origin){
        this.origin = origin;
    }

    public Aindexer(Corpus origin){
        this.origin = origin;
//        this.dataStructType = NAIVE;
//        this.parseRule = new SimpleParsingRule();
//        this.dataStruct = new NaiveIndexer(this.origin);
    }

    public abstract void indexFile(String indexFile);

    private void writeToFile() {
        try {
            String dictFile = this.origin.getPath() + this.dataStructType + "_index.cache";
            String hashCode = this.origin.getChecksum();
            FileOutputStream fileOut = new FileOutputStream(dictFile);

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(hashCode);

            objectOut.writeObject(getIndexType());

            objectOut.close();
            System.out.println("The Object was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected abstract String getIndexType();

    public Collection<Entry> indexCorpus(Corpus origin) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (Entry file : origin) {
            entries.add(this.indexEntry(file));
        }
        return entries;
    }


}
