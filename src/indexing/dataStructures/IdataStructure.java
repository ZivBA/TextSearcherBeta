package indexing.dataStructures;

import textStructure.BlockResult;
import textStructure.Corpus;
import textStructure.QueryResult;
import utils.Stemmer;

import java.util.Collection;
import java.util.List;

public interface IdataStructure {
    public List<QueryResult> searchWord(String word);
    public List<BlockResult> searchWordList(Collection<String> wordList);
    public List<QueryResult> searchMetaData(String word);
    Corpus getOrigin();


}
