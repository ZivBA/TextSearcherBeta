package textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Block {
    private final long endIdx;
    private final RandomAccessFile inputFile;
    private final long startIdx;

    public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
        this.inputFile = inputFile;
        this.startIdx = startIdx;
        this.endIdx = endIdx;

    }

    public long getStartIndex(){
        return startIdx;
    }

    public long getEndIndex(){
        return endIdx;
    }

    @Override
    public String toString() {
        try {
            inputFile.seek(startIdx);
            byte[] resultBytes = new byte[Math.toIntExact(endIdx - startIdx + 1)];
            inputFile.read(resultBytes);
            return new String(resultBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

}
