package textStructure;

import java.io.RandomAccessFile;

public class BlockResult extends Block{
    public BlockResult(RandomAccessFile inputFile, long startIdx, long endIdx) {
        super(inputFile, startIdx, endIdx);
    }
}
