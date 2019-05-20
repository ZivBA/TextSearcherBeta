package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleParsingRule extends AparsingRule {



    public SimpleParsingRule(RandomAccessFile file) {
        super(file);
    }

    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        return 0;
    }

    @Override
    public boolean hasNext() {
        nextBlock = null;
        byte[] temp = new byte[MAX_LINE_LENGTH];
        int prevIndex = nextIndex;
        try {
            while(nextIndex<inputFile.length()){

                inputFile.seek(nextIndex);

                if(inputFile.read(temp) == -1){
                    break;
                }
                String tempString = new String(temp);
                if(tempString.startsWith("\n")){
                    int start = prevIndex;
                    int end = nextIndex;
                    nextIndex++;
                    nextBlock = new Block(inputFile,start,end);
                }
                prevIndex = nextIndex;
                nextIndex = tempString.indexOf("\n")==-1 ? nextIndex + MAX_LINE_LENGTH : nextIndex + 1;
            }



        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return nextBlock != null;
    }

    @Override
    public Block next() {
        return nextBlock;
    }
}
