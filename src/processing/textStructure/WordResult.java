package processing.textStructure;

import java.io.IOException;

public class WordResult {
    private long idxInBlk;
    Block location;
    protected String[] content;

    private WordResult(Block loc, String[] word){
        this.content = word;
        this.location = loc;
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
        return content[0];
    }

    public long[] getOffsets() {
        long[] locs = new long[1];
        locs[0] = idxInBlk;
        return locs;
    }

    @Override
    public String toString(){
        String offsets = "offsets : ";
        for(long offset: getOffsets()){
            offsets += " " + offset;
        }
        return offsets;
    }


}
