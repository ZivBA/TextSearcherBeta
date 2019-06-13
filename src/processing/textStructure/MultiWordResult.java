package processing.textStructure;

import java.io.IOException;
import java.util.Arrays;

public class MultiWordResult extends WordResult implements Comparable<MultiWordResult> {
	private long[] wordPositions;
	private int confidence;


	private MultiWordResult(Block blk, String[] query, long idx) {
		super(blk, query, idx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param query The list of query words
	 * @param block The block where this result came from
	 * @param locs  The indices of the words in the block
	 */
	public MultiWordResult(String[] query, Block block, long[] locs) {
		this(block,query,locs[0]);
		wordPositions = locs;
		confidence = calcConfidence(locs);
	}

	/**
	 *
	 * @param locs
	 * @return
	 */
	private int calcConfidence(long[] locs) {
		int sum = 0;
		for(int i = 0; i < wordPositions.length - 1; i++) {
			sum+= Math.abs(wordPositions[i+1] - wordPositions[i]);
		}
		return sum;
	}

	@Override
	public int compareTo(MultiWordResult o) {

		if(o.confidence > this.confidence) {
			return -1;
		}else if(o.confidence< this.confidence) {
			return 1;
		}
		return 0;
	}

	@Override
	public String resultToString() throws IOException {
		Arrays.sort(wordPositions);
		String temp = this.location.extractFromBlock(wordPositions[0]-50, wordPositions[wordPositions.length-1]+70);
		int startIdx = temp.indexOf('\n');
		int endIdx = temp.lastIndexOf('\n');
		return temp.substring(Math.max(startIdx,0),endIdx);
	}

	@Override
	public long[] getOffsets() {
		return wordPositions;
	}

	@Override
	public String toString(){
		String offsets = super.toString();
		return offsets + "\nconfidence : " + confidence;
	}

}
