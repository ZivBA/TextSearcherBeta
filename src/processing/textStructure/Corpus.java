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
    private List<Entry> entryList;
    private IparsingRule parsingRule;
    private String corpusPath;

    public Corpus(String path, String parserName) throws IOException {
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */
        this.entryList = new LinkedList<>();
        this.corpusPath = path;
        this.parsingRule = ParsingRuleFactory.createRuleByName(parserName,path);
    }

    public void populate(){
        File corups = new File(corpusPath);
        populateEntryList(corups);
    }

    private void populateEntryList(File entryFile) {
        if(entryFile.isFile()){
            this.entryList.add(new Entry(entryFile.getAbsolutePath(), parsingRule));
            return;
        }
        if(entryFile.isDirectory()){
            for(File f: entryFile.listFiles()){
                populateEntryList(f);
            }
            return;
        }
        throw new RuntimeException("Bad file path " + entryFile.getAbsolutePath());
    }

    private List<File> getAllFiles(File root){
        List<File> curList = new LinkedList<>();
        if(root.isFile()){
            curList.add(root);
            return curList;
        }
        if(root.isDirectory()){
            for(File f: root.listFiles()){
                curList.addAll(getAllFiles(f));
            }

        }
        return curList;
    }
    public String getPath() {
        /*
        return a string representation of the path.
         */
        return corpusPath;
    }

    @Override
    public Iterator<Entry> iterator() {
        return this.entryList.iterator();
    }

    public String getChecksum() throws IOException {
        List<File> corpusFiles = getAllFiles(new File(this.corpusPath));
        StringBuilder result = new StringBuilder();
        for (File f : corpusFiles){
            result.append(MD5.getMd5(Files.readAllBytes(f.toPath())));
        }
        return MD5.getMd5(result.toString());
    }

    public IparsingRule getParsingRule() {
        return this.parsingRule;
    }
}
