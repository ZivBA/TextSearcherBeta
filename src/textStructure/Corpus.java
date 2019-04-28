package textStructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Corpus implements Iterable<Entry>{
    private List<Entry> entryList;

    public Corpus(Collection<String> inputFiles){

    }
    @Override
    public Iterator<Entry> iterator() {
        return this.entryList.iterator();
    }
}
