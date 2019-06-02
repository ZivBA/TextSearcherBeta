package indexing.textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;
public class Word {
	private final Block srcBlk;
	private final long srcBlkOffset;
	private final int length;
	private final int wordHash;

	public Word(Block source, long start, long end){
		this.srcBlk = source;
		this.srcBlkOffset = start;
		this.length = (int) (end-start);
		this.wordHash = extractWord().hashCode();
	}

	public Block getSrcBlk(){
		return this.srcBlk;
	}

	public String extractWord(){
		byte[] rawWord = new byte[length];
		RandomAccessFile theFile = this.srcBlk.getRAF();
		try {
			theFile.seek(srcBlk.getStartIndex()+srcBlkOffset);
			theFile.read(rawWord);
			return new String(rawWord);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public int getHash() {
		return this.wordHash;
	}
	public long getEntryIndex(){
		return this.srcBlk.getStartIndex()+this.srcBlkOffset;
	}
}
