package processing.textStructure;

import java.io.IOException;

public class WordResult implements Comparable<WordResult> {
    private long idxInBlk;  // The offset of the word within the block
    Block sourceBlock;       // The block in which this word was found
    protected String[] content; // The word(s) that were found


    private WordResult(Block loc, String[] word){

    }

    public WordResult(Block blk, String[] words, long idx) {
        this(blk, words);
        this.idxInBlk = idx;
    }
    public Block getBlock(){
        return this.sourceBlock;
    }

    public String getWord(){
        return this.content[0];
    }

    public String resultToString() throws IOException {

    }

    public String getSourceEntry() {
        return this.sourceBlock.getEntryName();
    }

	@Override
	public int compareTo(WordResult wordResult) {

	}
}
