package textStructure;

import rules.IparsingRule;
import rules.ParsingRuleFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final IparsingRule parseRule;
    private List<Block> blockList;
    private String sourceFile;

    public Entry(String filePath, String parseRule) {
        this.sourceFile = filePath;

        try {
            this.parseRule = ParsingRuleFactory.createRuleByName(parseRule,filePath);
            this.blockList = this.parseRule.parseFile(new RandomAccessFile(sourceFile, "r"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
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
