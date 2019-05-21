package textStructure;

import rules.AparsingRule;
import rules.ParsingRuleFactory;

import java.io.FileNotFoundException;
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

        try {
            this.parseRule = ParsingRuleFactory.createRuleByName(parseRule,filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        blockList = new LinkedList<>();
        initBlockList();
    }

    private void initBlockList() {
        while(parseRule.hasNext()){
            Block next = parseRule.next();
            System.out.println(next.toString());
            System.out.println(next.toString().length());

            blockList.add(next);
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
