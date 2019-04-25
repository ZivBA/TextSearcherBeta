package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.util.Collection;

public interface IparsingRule {
    public Block parseBlock(Collection<String> rawBlock);
    public int getWordDistance(WordResult first, WordResult second);


}
