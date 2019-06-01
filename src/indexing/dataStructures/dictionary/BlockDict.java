package indexing.dataStructures.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import textStructure.Block;

public class BlockDict {
	
	private Block block;
	private Map<Integer, List<Long>> dict;
	
	public BlockDict(Block block) {
		this.block= block;
		this.dict = new HashMap<>();
	}

	public Map<Integer, List<Long>> getDict() {
		// TODO Auto-generated method stub
		return dict;
	}

	public Block geBlock() {
		// TODO Auto-generated method stub
		return block;
	}
	
}
