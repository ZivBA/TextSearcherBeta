package processing.textStructure;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block {
	private long endIdx;
	private RandomAccessFile inputFile;
	private long startIdx;

	public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {

	}

	public long getStartIndex() {
		return startIdx;
	}

	public long getEndIndex() {
		return endIdx;
	}

	public RandomAccessFile getRAF() {
		return inputFile;
	}


	@Override
	public String toString() {

	}



	public String getMeta() {

	}

	public String getEntryName() {

	}
}
