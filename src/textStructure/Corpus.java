package textStructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Corpus implements Iterable<String>{
    private List<Entry> entryList;

    public Corpus(Collection<String> inputFiles){

    }
    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
