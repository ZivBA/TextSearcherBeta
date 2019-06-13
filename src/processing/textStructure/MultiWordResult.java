package processing.textStructure;

import java.io.IOException;
import java.util.Arrays;

/**
 * This class defines a query result for multiple non-consecutive words.
 */
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
	 * Calculate the confidence level of a result, defined by the sum of word distances.
	 * @param locs  The locations of the query words in the text
	 * @return  The sum of distances
	 */
	private int calcConfidence(long[] locs) {
		int sum = 0;
		for(int i = 0; i < wordPositions.length - 1; i++) {
			sum+= Math.abs(wordPositions[i+1] - wordPositions[i]);
		}
		return sum;
	}

	/**
	 * Comparator for multy-word results
	 * @param o The other result to compare against
	 * @return  int representing comparison result, according to the comparable interface.
	 */
	@Override
	public int compareTo(MultiWordResult o) {

		if(o.confidence > this.confidence) {
			return -1;
		}else if(o.confidence< this.confidence) {
			return 1;
		}
		return 0;
	}

	/**
	 * Extract a string that contains all words in the multy-word-result
	 * This should be a sentance starting at the word with the minimal location (index) and ending
	 * at the first line-break after the last word
	 * @return  A piece of text containing all query words
	 */
	@Override
	public String resultToString() throws IOException {
		Arrays.sort(wordPositions);
		String temp = this.location.extractFromBlock(wordPositions[0]-50, wordPositions[wordPositions.length-1]+70);
		int startIdx = temp.indexOf('\n');
		int endIdx = temp.lastIndexOf('\n');
		return temp.substring(Math.max(startIdx,0),endIdx);
	}

//	@Override
//	public long[] getOffsets() {
//		long offsets[] = new long[wordPositions.length];
//		for(int i = 0; i < wordPositions.length ; i++){
//			offsets[i] = wordPositions[i] - getBlock().getStartIndex();
//		}
//		return offsets;
//	}

//	@Override
//	public String toString(){
//		String offsets = super.toString();
//		return offsets + "\nconfidence : " + confidence;
//	}

}
