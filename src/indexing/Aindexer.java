package indexing;

import processing.parsingRules.IparsingRule;
import indexing.textStructure.Block;
import indexing.textStructure.Corpus;
import indexing.textStructure.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import processing.searchStrategies.IQuerySearch;
import processing.utils.WrongMD5ChecksumException;

public abstract class Aindexer<T extends IQuerySearch> {
    static final String DICT = "dict";
    static final String NAIVE = "naive";
    static final String RK = "naiveRK";
    String dataStructType;
    protected Corpus origin;

    public void setOrigin(Corpus origin){
        this.origin = origin;
        
    }

    public Aindexer(Corpus origin){
        this.origin = origin;
    }

    public void index() {
    	try {
			readIndexedFile();
		} catch (FileNotFoundException | WrongMD5ChecksumException e) {
    	    origin.populate();
			indexCorpus();
			//writeToFile();
		}
    	
    }
    
    public abstract T asSearchInterface();

    protected abstract void indexCorpus();
	
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

    protected abstract void castRawData(Object readObject);

    private String getIndexedPath() {
		return origin.getPath() + "_cache";
	}

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

    protected void writeParams( ObjectOutputStream objectOut) throws IOException {
    	String hashCode = this.origin.getChecksum();
        objectOut.writeObject(hashCode);

        objectOut.writeObject(getIndexType());
        objectOut.writeObject(this.origin);
    }
	protected void writeDataStructure(ObjectOutputStream objectOut) throws IOException {

    }
	public abstract IparsingRule getParseRule();

    protected abstract String getIndexType();

	public Corpus getCorpus(){return  this.origin;}
}
