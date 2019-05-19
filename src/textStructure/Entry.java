package textStructure;

import indexing.dataStructures.IdataStructure;
import rules.IparsingRule;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Entry implements Iterable<Block>{
    private final IparsingRule parseRule;
    private List<Block> blockList;
    private Corpus origin;
    private String sourceFile;

    public Entry(String filePath, IparsingRule parseRule) {
        this.sourceFile = filePath;
        this.parseRule = parseRule;
        blockList = new LinkedList<>();
        initBlockList();
    }

    private void initBlockList() {
        try {
            Block block = parseRule.parseBlock(new RandomAccessFile(getFilePath(),"r"),0);
            while(block.getStartIndex() != block.getEndIndex()){
                blockList.add(block);
                block = parseRule.parseBlock(new RandomAccessFile(getFilePath(),"r"),block.getEndIndex());
            }
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
