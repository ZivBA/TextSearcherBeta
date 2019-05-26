package textStructure;

import java.io.RandomAccessFile;

public class LinesBlock extends Block {
	private int lineEndIndex;
	public LinesBlock(RandomAccessFile inputFile) {
		super(inputFile);
		lineEndIndex = -1;
	}
	
	public LinesBlock setLineEnd(int blockLineEnd) {
		lineEndIndex = blockLineEnd;
		return this;
	}

	public int getLastLine() {
		// TODO Auto-generated method stub
		return lineEndIndex;
	}



}
