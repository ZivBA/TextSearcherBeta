package processing.textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Block {
	private long endIdx;
	private RandomAccessFile inputFile;
	private long startIdx;
	private List<String> metaData = new ArrayList<>();

	public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
		this.inputFile = inputFile;
		this.startIdx = startIdx;
		this.endIdx = endIdx;

	}

//	public Block(RandomAccessFile inputFile) {
//		this(inputFile, -1, -1);
//	}

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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Block))
			return false;
		Block other = (Block) o;
		return this.startIdx == ((Block) o).startIdx &&
				this.endIdx == ((Block) o).endIdx &&
				this.inputFile == ((Block) o).inputFile;
	}
	@Override
	public int hashCode(){
		return (Long.valueOf(endIdx + startIdx + metaData.hashCode() + inputFile.hashCode())).hashCode();
	}

	public void setMetadata(List<String> metaData) {
		this.metaData = metaData;
	}

	public String extractFromBlock(long startIdx, long endIdx) throws IOException {
		startIdx = startIdx < 0 ? 0 : startIdx;
		this.inputFile.seek(startIdx);
		byte[] rawString = new byte[Math.toIntExact(endIdx - startIdx + 1)];
		this.inputFile.read(rawString);
		return new String(rawString);
	}

	public RandomAccessFile getRAF() {
		return inputFile;
	}

	public List<String> getMeta() {
		return this.metaData;
	}
}
