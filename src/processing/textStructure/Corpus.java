package processing.textStructure;

import processing.parsingRules.IparsingRule;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a body of works - anywhere between one and thousands of documents sharing the same structure and that can be parsed by the same parsing rule.
 */
public class Corpus implements Iterable<Entry>, Serializable {
	public static final long serialVersionUID = 1L;
    private List<Entry> entryList;
    private IparsingRule parsingRule;
    private String corpusPath;

    public Corpus(String path, String parserName) throws IOException {
   
    }

	/**
	 * This method populates the Block lists for each Entry in the corpus.
	 */
	public void populate(){
    
    }
    

	/**
	 * The path to the corpus folder
	 * @return A String representation of the absolute path to the corpus folder
	 */
	public String getPath() {
		
    }

	/**
	 * Iterate over Entry objects in the Corpus
	 * @return An entry iterator
	 */
	@Override
    public Iterator<Entry> iterator() {
        return this.entryList.iterator();
    }

	/**
	 * Return the checksum of the entire corpus. This is an MD5 checksum which represents all the files in the corpus.
	 * @return A string representing the checksum of the corpus.
	 * @throws IOException if any file is invalid.
	 */
	public String getChecksum() throws IOException {
    
    }

	/**
	 * Return the parsing rule used for this corpus
	 * @return the parsing rule used for this corpus
	 */
	public IparsingRule getParsingRule() {
        return this.parsingRule;
    }

	/**
	 * Update the RandomAccessFile objects for the Entries in the corpus, if it was loaded from cache.
	 */
	public void updateRAFs() {
	
	}
}
