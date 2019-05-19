package textStructure;

import rules.IparsingRule;
import utils.MD5;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Corpus implements Iterable<Entry>{
    private List<Entry> entryList;
    private IparsingRule parsingRule;
    public Corpus(String path, IparsingRule parsingRule){
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */
        entryList = new LinkedList<>();
        File corups = new File(path);
        populateEntryList(corups);
        this.parsingRule = parsingRule;
    }

    private void populateEntryList(File entryFile) {
        if(entryFile.isFile()){
            entryList.add(new Entry(entryFile.getAbsolutePath(),parsingRule));
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

    public static String getPath() {
        /*
        return a string representation of the path.
         */
    }

    @Override
    public Iterator<Entry> iterator() {
        return this.entryList.iterator();
    }

    public String getChecksum() {
        String result = "";
        for (Entry ent : entryList){
            result+= MD5.getMd5(ent.getBytes());
        }
        return MD5.getMd5(result);
    }
}
