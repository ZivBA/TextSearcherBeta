package textStructure;

import utils.MD5;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Corpus implements Iterable<Entry>{
    private List<Entry> entryList;

    public Corpus(String path){
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */
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
