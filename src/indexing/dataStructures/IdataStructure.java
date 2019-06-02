package indexing.dataStructures;

import indexing.textStructure.Corpus;
import indexing.textStructure.WordResult;

import java.util.List;

public interface IdataStructure {
    public List<WordResult> searchWord(String word);
    public List<WordResult> searchMetaData(String word);
    Corpus getOrigin();


}
