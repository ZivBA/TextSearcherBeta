package dataStructures;

import processing.parsingRules.IparsingRule;
import processing.searchStrategies.IsearchStrategy;
import processing.textStructure.Corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utils.WrongMD5ChecksumException;

/**
 * The abstract class describing the necessary methods and common implementations of all indexing data structures.
 * @param <T>   The search stratagy used by this indexing engine. Can be any class that implements the
 *           IsearchStrategy interface.
 */
public abstract class Aindexer<T extends IsearchStrategy> {
	public static enum IndexTypes {DICT, NAIVE, NAIVE_RK, SUFFIX_TREE}
    IndexTypes dataStructType;
    protected Corpus origin;


	/**
	 * Basic constructor accepting origin Corpus
	 * @param origin    The corpus indexed in this DS.
	 */
	public Aindexer(Corpus origin){
        this.origin = origin;
    }

	/**
	 * Main indexing method. Common implementation trying to read indexed cache file
	 */
	public void index() {
    	try {
			readIndexedFile();
			System.out.println("success on reading index from file");
		} catch (FileNotFoundException | WrongMD5ChecksumException e) {
    	    origin.populate();
			indexCorpus();
			System.out.println("success on indexing file");

			//writeToFile();
		}
    	
    }

	/**
	 * get the backing search interface.
	 * @return  The search interface implementation used by this indexer.
	 */
	public abstract T asSearchInterface();

	/**
	 * Index the Corpus internally using the parsing rules defined for this datastructure.
	 */
    protected abstract void indexCorpus();

	/**
	 * Try to read a cached index file if one already exists.
	 * @throws FileNotFoundException
	 * @throws WrongMD5ChecksumException
	 */
	private void readIndexedFile() throws FileNotFoundException, WrongMD5ChecksumException {
    	FileInputStream fi = new FileInputStream(new File(getIndexedPath()));
    	try {
    		ObjectInputStream oi = new ObjectInputStream(fi);
    		String oldHashCode = (String) oi.readObject();
    		if (oldHashCode.equals(this.origin.getChecksum())) {
                this.castRawData(oi.readObject());
            } else{
    		    throw new WrongMD5ChecksumException();
            }
    	}catch(IOException | ClassNotFoundException e) {
    		throw new RuntimeException(e.getMessage());
    	}


		
	}

	/**
	 * Convert a read object file into the indexer's specific data type.
	 * @param readObject    the input object to cast
	 */
    protected abstract void castRawData(Object readObject);

	/**
	 * Getter for the cached index file.
	 * @return  the path to the cached index file.
	 */
	private String getIndexedPath() {
		return origin.getPath() + "_cache";
	}

	/**
	 * Write the indernal index into file.
	 */
	private void writeIndexFile() {
        try {
            String indexPath = getIndexedPath();
            FileOutputStream fileOut = new FileOutputStream(indexPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            writeParams(objectOut);
            objectOut.close();
            System.out.println("The Object was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

	/**
	 * write the parameters of the indexer
	 * @param objectOut the output stream to write into
	 * @throws IOException  If the object stream misbehaves
	 */
    protected void writeParams( ObjectOutputStream objectOut) throws IOException {
    	String hashCode = this.origin.getChecksum();
        objectOut.writeObject(hashCode);

        objectOut.writeObject(getIndexType());
        objectOut.writeObject(this.origin);
    }

	/**
	 * Extract the parsing rule used for indexing this data structure.
	 * @return  an instance of a parser implementing IparsingRule
	 */
	public abstract IparsingRule getParseRule();

	/**
	 * simple getter
	 * @return  the DS type
	 */
    protected abstract IndexTypes getIndexType();

	/**
	 * simple getter
	 * @return  Regerence to the origin Corpus
	 */
	public Corpus getCorpus(){return  this.origin;}
}
