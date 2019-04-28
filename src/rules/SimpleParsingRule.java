package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.RandomAccess;

public class SimpleParsingRule implements IparsingRule {
    @Override
    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
        byte[] temp = new byte[10];
        String tempString;
        long endIdx;
        try {
            for (endIdx = startIdx; endIdx < startIdx + 256; endIdx += 10){
                inputFile.seek(endIdx);
                inputFile.read(temp);
                tempString = new String(temp);
                if (tempString.matches("\\n\\n")){
                    break;
                }
            }
            return new Block(inputFile, startIdx, endIdx);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public int getWordDistance(WordResult first, WordResult second) {
        return 0;
    }
}
