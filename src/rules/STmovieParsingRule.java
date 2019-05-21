package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class STmovieParsingRule extends AparsingRule {
    static final private String START_BLOCK_REGEX = "^[0-9]+.*";
    public STmovieParsingRule(RandomAccessFile file) {
        super(file);
    }

    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
        //TODO implement regex parsing using the following rules:
        /*
            each scene starts with a line like "               1   BLACK                                                        1"
            where the first and last numbers represent the scene number.
            within a scene there are lines like "                   Absolute quiet. SOUND bleeds in. Low level b.g."
            which are descriptions and are not interesting except for indexing words
            there are lines like: "                                           SAAVIK'S VOICE"
            where there are only upper case letters, describing who is speaking and should be stored within the
            block MD as charachters within the scene.
            lines less indented but also only uppercase like "                   INT. ENTERPRISE BRIDGE"
            are scene locations and should also be stored in MD as locations in scene
            lines like "                             Leaving Section Fourteen for"
            are less indented than scene description, and represent dialog.

            The indexer must store, efficiently, the characters' names and scene locations within the block MD for pretty printing the results.

         */
        return null;

    }

    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        // this should actually be a comparator for wordResults, where two word results are considered equal if they come from the same block
        // if they come from different blocks, then either both blocks contain all the query words, in which case the order is according to scene number
        // otherwise, whichever block has more queryWords gets a higher score (is grater than the other)
        return -1;
    }


    @Override
    protected BlockLocation getNewBlockLocation(long startIndex) {
//        long index = startIndex;
//
//        try {
//
//            inputFile.seek(index);
//            String line = inputFile.readLine();
//            while(line!=null){
//                if(line.trim().matches(START_BLOCK_REGEX)){
//                    return index;
//
//                }
//                index += line.length();
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return index;
        return null;
    }

    @Override
    public Block next() {
//        long start = getNewBlockLocation(nextIndex);
//
//        try {
//            inputFile.seek(start);
//            String line = inputFile.readLine();
//            nextIndex = getNewBlockLocation(start + line.length());
//            nextBlock = new Block(inputFile,start,nextIndex - 1);
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return nextBlock;
    }
}
