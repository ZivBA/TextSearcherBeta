package processing.textStructure;

import java.io.IOException;

public class WordResult {
    private long idxInBlk;
    Block location;
    protected String[] content;

    private WordResult(Block loc, String[] word){

    }

    public WordResult(Block blk, String[] words, long idx) {
        this(blk, words);
        this.idxInBlk = idx;
    }
    public Block getBlock(){
        return this.location;
    }

    public String[] getWord(){
        return this.content;
    }

    public String resultToString() throws IOException {

    }

    public String getSourceEntry() {
        return this.location.getEntryName();
    }
}
