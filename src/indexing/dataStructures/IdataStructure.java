package indexing.dataStructures;

import textStructure.BlockResult;
import textStructure.Corpus;
import textStructure.WordResult;
import utils.Stemmer;

import java.util.Collection;
import java.util.List;

public interface IdataStructure {
    public List<WordResult> searchWord(String word);
    public List<BlockResult> searchWordList(Collection<String> wordList);
    public List<WordResult> searchMetaData(String word);
    Corpus getOrigin();
    final Stemmer stemmer = new Stemmer();

}
