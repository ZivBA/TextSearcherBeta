package rules;

import textStructure.Block;
import textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class STtvSeriesParsingRule implements IparsingRule {
    @Override
    public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
        //TODO implement regex parsing using the following rules:
        /*
            each scene starts with a line like "1    EXT. SPACE - STARSHIP (OPTICAL)" (no tabs)
            where the first number represent the scene number.
            within a scene there are lines like "	The U.S.S. Enterprise NCC 1701-D traveling at warp speed" (one tab)
            which are descriptions and are not interesting except for indexing words
            there are lines like: "					PICARD V.O." (five tabs)
            where there are only upper case letters, describing who is speaking and should be stored within the
            block MD as charachters within the scene.
            lines like "			... my crew we are short in" (three tabs)
            represent dialog.

            The indexer must store, efficiently, the characters' names and scene locations within the block MD for pretty printing the results.
            */
    }

    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        return 0;
    }
}
