package processing.textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an arbitrary block of text within a file
 */
public class Block implements Serializable {
	public static final long serialVersionUID = 1L;

	private long endIdx;
	transient RandomAccessFile inputFile;
	private long startIdx;
	private List<String> metaData = new ArrayList<>();

	public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
		this.inputFile = inputFile;
		this.startIdx = startIdx;
		this.endIdx = endIdx;

	}


	public String getEntryName(){return "";};

	public Block(Block blk) {
		this.inputFile = blk.inputFile;
		this.metaData = blk.metaData;
		this.startIdx = blk.startIdx;
		this.endIdx = blk.endIdx;
	}

	public long getStartIndex() {
		return startIdx;
	}

	public long getEndIndex() {
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


	public void setMetadata(List<String> metaData) {
		this.metaData = metaData;
	}

	String extractFromBlock(long startIdx, long endIdx) throws IOException {
		startIdx = startIdx < 0 ? 0 : startIdx;
		this.inputFile.seek(startIdx);
		byte[] rawString = new byte[Math.toIntExact(endIdx - startIdx + 1)];
		this.inputFile.read(rawString);
		return new String(rawString);
	}

	public RandomAccessFile getRAF() {
		return inputFile;
	}

	public List<String> getMetadata() {
		return this.metaData;
	}
}
