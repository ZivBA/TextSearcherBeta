package textStructure;

public class QueryResult {
    private int idxInBlk;
    private Block location;
    private String content;

    private QueryResult(Block loc, String word){
        this.content = word;
        this.location = loc;
    }

    public QueryResult(Block blk, String word, int idx) {
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
