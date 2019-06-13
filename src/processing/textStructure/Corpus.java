package processing.textStructure;

import processing.parsingRules.IparsingRule;
import processing.parsingRules.ParsingRuleFactory;
import utils.MD5;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
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
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */
        this.entryList = new LinkedList<>();
        this.corpusPath = path;
        this.parsingRule = ParsingRuleFactory.createRuleByName(parserName);
    }

	/**
	 * This method populates the Block lists for each Entry in the corpus.
	 */
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

	/**
	 * The path to the corpus folder
	 * @return A String representation of the absolute path to the corpus folder
	 */
	public String getPath() {

        return corpusPath;
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
        List<File> corpusFiles = getAllFiles(new File(this.corpusPath));
        StringBuilder result = new StringBuilder();
        for (File f : corpusFiles){
            result.append(MD5.getMd5(Files.readAllBytes(f.toPath())));
        }
        return MD5.getMd5(result.toString());
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
		for (Entry ent : this.entryList){
			ent.updateRAFs();
		}
	}
}
