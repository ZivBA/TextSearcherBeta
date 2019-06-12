package processing.textStructure;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        String blkAsString = getBlock().toString();
        long[] offsets = getOffsets();
        long start = 999999999;
        long end = -1;
        for(long offset: offsets){
            System.out.println(offset);
            if(offset<start) start = offset;
            if(offset>end) end = offset;
        }
        end = blkAsString.indexOf("\n", (int)end);
        if(end==-1){
            end = blkAsString.length();
        }
        System.out.println("start " + start + " end " + end);
        return blkAsString.substring((int)start,(int) end);
    }


}
