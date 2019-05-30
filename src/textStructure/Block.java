package textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Block {
    private long endIdx;
    private RandomAccessFile inputFile;
    private long startIdx;
	private List<String> metaData;

	public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
        this.inputFile = inputFile;
        this.startIdx = startIdx;
        this.endIdx = endIdx;

    }

    public Block(RandomAccessFile inputFile) {
		this(inputFile,-1,-1);
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
            inputFile.seek(startIdx);
            inputFile.read(resultBytes);
            return new String(resultBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

	public Block setStart(long start) {
		this.startIdx = start;
		return this;
		
	}

	public Block setEnd(long end) {
		endIdx = end;
		return this;
	}


	public void setMetadata(List<String> metaData) {
		this.metaData = metaData;
	}
}
