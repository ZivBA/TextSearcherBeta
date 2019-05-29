package textStructure;

import rules.LineParsingRule;
import rules.ParsingRuleFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final LineParsingRule parseRule;
    private List<Block> blockList;
    private String sourceFile;

    public Entry(String filePath, String parseRule) {
        this.sourceFile = filePath;

        try {
            this.parseRule = ParsingRuleFactory.createRuleByName(parseRule,filePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.blockList = initBlockList();
    }

    private List<Block> initBlockList() {
        List<Block> newList = new LinkedList<>();
        while(parseRule.hasNext()){
            Block next = parseRule.next();
            System.out.println((int)next.toString().charAt(9));
            System.out.println(next.toString().length());

            newList.add(next);
        }
        return newList;
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
