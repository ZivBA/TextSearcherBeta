package indexing.dataStructures.naiveSearch;

import textStructure.*;
import utils.Stemmer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NaiveIndexer implements indexing.dataStructures.IdataStructure {

    private Corpus corpus;
    private final Stemmer stemmer;

    public NaiveIndexer(){
        this.stemmer = new Stemmer();
    }

    public NaiveIndexer(Corpus corpus){
        this();
        this.corpus = corpus;
    }

    @Override
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

    @Override
    public List<BlockResult> searchWordList(Collection<String> wordList) {
        //TODO implement
        return null;
    }

    @Override
    public Corpus getOrigin() {
        return this.corpus;
    }

    @Override
    public void indexFile(String inputFile) {
        // naive indexer doesn't really index anything! muahahah...
    }
}
