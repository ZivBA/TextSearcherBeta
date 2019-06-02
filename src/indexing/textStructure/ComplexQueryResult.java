package indexing.textStructure;

import java.io.IOException;
import java.util.Arrays;

public class ComplexQueryResult extends WordResult implements Comparable<ComplexQueryResult> {
	private long[] wordPositions;
	private int confidence;
	
	public ComplexQueryResult(Block blk, String[] query, long idx) {
		super(blk, query, idx);
		// TODO Auto-generated constructor stub
	}

	public ComplexQueryResult(String[] query, Block block, long[] locs) {
		this(block,query,locs[0]);
		wordPositions = locs;
		confidence = calcConfidence(locs);
	}

	private int calcConfidence(long[] locs) {
		int sum = 0;
		for(int i = 0; i < wordPositions.length - 1; i++) {
			sum+= wordPositions[i+1] - wordPositions[i];
		}
		return sum;
	}

	@Override
	public int compareTo(ComplexQueryResult o) {
		if(o.confidence > this.confidence) {
			return 1;
		}else if(o.confidence< this.confidence) {
			return -1;
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

}
