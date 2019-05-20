package textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Block {
    private final long endIdx;
    private final RandomAccessFile inputFile;
    private final long starIdx;

    public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
        this.inputFile = inputFile;
        this.starIdx = startIdx;
        this.endIdx = endIdx;
    }

    public long getStartIndex(){
        return starIdx;
    }

    public long getEndIndex(){
        return endIdx;
    }

    public String asString() {
        try {
            inputFile.seek(starIdx);
            byte[] resultBytes = new byte[Math.toIntExact(endIdx - starIdx + 1)];
            inputFile.read(resultBytes);
            return new String(resultBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

}
