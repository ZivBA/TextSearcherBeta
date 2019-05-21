package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleParsingRule extends AparsingRule {


    private String lines[];
    private int currLine;
    public SimpleParsingRule(RandomAccessFile file) {
        super(file);
        lines = fileAsString.split("\n\n|^\n");
        currLine = 0;
    }

    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        return 0;
    }


    @Override
    public Block next() {
        BlockLocation loc = getNewBlockLocation(nextIndex);

        nextBlock = new Block(inputFile,loc.getStart(),loc.getEnd()-1);
        nextIndex = loc.getEnd();
        currLine++;
        return nextBlock;
    }

    @Override
    protected BlockLocation getNewBlockLocation(long fromIndex) {
        String line;
        try{
            line = lines[currLine];
            while(line.equals("")){
                currLine++;
                line = lines[currLine];
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }


        long start = fileAsString.indexOf(line,(int)fromIndex);

        return new BlockLocation(start,start + line.length());

    }
}
