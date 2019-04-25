package textStructure;

import indexing.algorithms.IsearchAlgorithm;
import rules.IparsingRule;

import java.util.List;

public class Entry {
    private final IparsingRule parseRule;
    private final IsearchAlgorithm index;
    private List<Block> blockList;
    private Corpus origin;

    public Entry(IsearchAlgorithm searchAlg, IparsingRule parseRule) {
        this.index = searchAlg;
        this.parseRule = parseRule;
        this.origin = searchAlg.getOrigin();
    }
}
