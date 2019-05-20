package textStructure;

import rules.AparsingRule;
import rules.ParsingRuleFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final AparsingRule parseRule;
    private List<Block> blockList;
    private Corpus origin;
    private String sourceFile;

    public Entry(String filePath, String parseRule) {
        this.sourceFile = filePath;
        this.parseRule = ParsingRuleFactory.createRuleByName(parseRule,filePath);
        blockList = new LinkedList<>();
        initBlockList();
    }

    private void initBlockList() {
        while(parseRule.hasNext()){
            blockList.add(parseRule.next());
        }
    }

    @Override
    public Iterator<Block> iterator() {
        return this.blockList.iterator();
    }

    public String getFilePath() {
        return this.sourceFile;
    }

    public byte[] getBytes() {
        return new byte[0];
    }
}
