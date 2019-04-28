package indexing.dataStructures.naiveSearch;

import indexing.dataStructures.IdataStructure;
import textStructure.*;
import utils.Stemmer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NaiveSearch implements IdataStructure {

    private Corpus corpus;


    public NaiveSearch(Corpus corpus){
        this.corpus = corpus;
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
        return null;
    }

    @Override
    public List<WordResult> searchMetaData(String word) {
        return null;
    }

    public Corpus getOrigin() {
        return this.corpus;
    }

    @Override
    public void writeToFile() {

    }

    @Override
    public void indexCorpus(Corpus corpus) {

    }

    public void indexFile(String inputFile) {
        // naive indexer doesn't really index anything! muahahah...
    }
}
