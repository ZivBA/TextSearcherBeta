package indexing;

import textStructure.Corpus;
import textStructure.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import search.IQuerySearch;

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
		} catch (FileNotFoundException e) {
    	    origin.populate();
			indexCorpus();
			//writeToFile();
		}
    	
    }
    
    public abstract T asSearchInterface();

    protected abstract void indexCorpus();
	
	private void readIndexedFile() throws FileNotFoundException {
    	FileInputStream fi = new FileInputStream(new File(getIndexedPath()));
    	try {
    		ObjectInputStream oi = new ObjectInputStream(fi);
    		String oldHashCode = (String) oi.readObject();
    		HashMap<Integer, List<Long>> oldMap = (HashMap<Integer, List<Long>>) oi.readObject();
    	}catch(IOException | ClassNotFoundException e) {
    		throw new RuntimeException(e.getMessage());
    	}


		
	}
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

    protected abstract String getIndexType();
    



}
