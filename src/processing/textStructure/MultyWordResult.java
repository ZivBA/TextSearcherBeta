package processing.textStructure;

/**
 * This class defines a query result for multiple non-consecutive words.
 */
public class MultyWordResult extends WordResult implements Comparable<MultyWordResult> {

	/**
	 * Constructor matching Super
	 * @param blk
	 * @param words
	 * @param idx
	 */
	public MultyWordResult(Block blk, String words, long idx) {
		super(blk, words, idx);
	}

	/**
	 * Calculate the confidence level of a result, defined by the sum of word distances.
	 * @param locs  The locations of the query words in the text
	 * @return  The sum of distances
	 */
	private int calcConfidence(long[] locs) {
		//TODO implement me!
	}

	/**
	 * Comparator for multy-word results
	 * @param o The other result to compare against
	 * @return  int representing comparison result, according to the comparable interface.
	 */
	@Override
	public int compareTo(MultyWordResult o) {
		//TODO implement me!
	}

	/**
	 * Extract a string that contains all words in the multy-word-result
	 * This should be a sentance starting at the word with the minimal location (index) and ending
	 * at the first line-break after the last word
	 * @return  A piece of text containing all query words
	 */
	@Override
	public String resultToString() {
		//TODO implement me!
	}

}
