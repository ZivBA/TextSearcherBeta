package textStructure;

import java.io.RandomAccessFile;

public class LineBlock extends Block {
	private int lineEndIndex;
	public LineBlock(RandomAccessFile inputFile) {
		super(inputFile);
		lineEndIndex = -1;
	}
	
	public LineBlock setLineEnd(int blockLineEnd) {
		lineEndIndex = blockLineEnd;
		return this;
	}

	public int getLastLine() {
		// TODO Auto-generated method stub
		return lineEndIndex;
	}



}
