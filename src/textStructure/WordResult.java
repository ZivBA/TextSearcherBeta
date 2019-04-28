package textStructure;

public class WordResult {
    private int idxInBlk;
    private Block location;
    private String content;

    public WordResult(Block loc, String word){
        this.content = word;
        this.location = loc;
    }

    public WordResult(Block blk, String word, int idx) {
        this(blk, word);
        this.idxInBlk = idx;
    }
    public Block getBlock(){
        return this.location;
    }
    public String getWord(){
        return this.content;
    }
}
