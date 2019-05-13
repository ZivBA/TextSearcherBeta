package indexing.dataStructures.naive;

import indexing.Aindexer;
import indexing.dataStructures.IdataStructure;
import textStructure.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NaiveIndexer extends Aindexer implements IdataStructure {

    private Corpus corpus;


    @Override
    public Entry indexEntry(Entry inputEntry) {
        //implementation here
    }

    public NaiveIndexer(Corpus corpus){
        super(corpus);
    }

    public List<WordResult> searchWord(String word) {
        List<WordResult> results = new ArrayList<>();
        for (Entry entry : this.corpus){
            for (Block blk : entry){
                this.searchBlock(blk, results, word);
            }
        }
        return results;
    }

    private void searchBlock(Block blk, List<WordResult> results, String word) {
        for (String newWord : blk.toString().split("\\s+")){
            if (this.stemmer.stem(word).equals(this.stemmer.stem(newWord))){
                results.add(new WordResult(blk, word));
            }
        }
    }

    public List<BlockResult> searchWordList(Collection<String> wordList) {
        //TODO implement
        //implementation here
    }

    @Override
    public List<WordResult> searchMetaData(String word) {
        return null;
    }

    public Corpus getOrigin() {
        return this.corpus;
    }


    @Override
    protected String getIndexType() {
        return "naive_indexer";
    }

    @Override
    public Collection<Entry> indexCorpus(Corpus corpus) {
        //implementation here
    }

    public void indexFile(String inputFile) {
        // naive indexer doesn't really index anything! muahahah...
    }
}
