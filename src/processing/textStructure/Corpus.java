package processing.textStructure;

import processing.parsingRules.IparsingRule;
import processing.parsingRules.ParsingRuleFactory;
import utils.MD5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Corpus implements Iterable<Entry>{

    public Corpus(String path, String parserName) throws IOException {
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */
    }


    public String getPath() {
        /*
        return a string representation of the path.
         */
    }

    /**
     * Iterate over Entry objects in the Corpus
     * @return  An Entry iterator
     */
    @Override
    public Iterator<Entry> iterator() {

    }

    public String getChecksum() throws IOException {

    }

    public IparsingRule getParsingRule() {
    }
}
