package textStructure;

import indexing.dataStructures.IdataStructure;
import rules.IparsingRule;

import java.util.Iterator;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final IparsingRule parseRule;
    private final IdataStructure index;
    private List<Block> blockList;
    private Corpus origin;
    private String sourceFile;

    public Entry(IdataStructure searchAlg, IparsingRule parseRule) {
        this.index = searchAlg;
        this.parseRule = parseRule;
        this.origin = searchAlg.getOrigin();
    }

    @Override
    public Iterator<Block> iterator() {
        return this.blockList.iterator();
    }

    public String getFile() {
        return this.sourceFile;
    }

    public byte[] getBytes() {
        return new byte[0];
    }
}
