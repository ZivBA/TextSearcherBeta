package textStructure;

public class ComplexQueryResult extends QueryResult implements Comparable<ComplexQueryResult> {
	private int[] wordPositions;
	private int confidence;
	
	public ComplexQueryResult(Block blk, String query, int idx) {
		super(blk, query, idx);
		// TODO Auto-generated constructor stub
	}

	public ComplexQueryResult(String query, Block block, int[] locs) {
		this(block,query,locs[0]);
		wordPositions = locs;
		confidence = calcConfidence(locs);
	}

	private int calcConfidence(int[] locs) {
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

}
