package indexing.dataStructures.dictionary;

import indexing.Aindexer;
import indexing.dataStructures.IdataStructure;
import search.IQuerySearch;
import textStructure.BlockResult;
import textStructure.Corpus;
import textStructure.Entry;
import textStructure.QueryResult;
import utils.Stemmer;
import utils.Stopwords;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static utils.MD5.getMd5;

public class DictionaryIndexer extends Aindexer {
	private String dictFile;
	private String hashCode;
	private HashMap<Integer, List<Long>> dict = new HashMap<>();
	private String fileName;
	private static final Stemmer STEMMER = new Stemmer();
	public static final String TYPE_NAME = "dictionary_indexer";

	public void indexEntry(Entry inputEntry) {

	}

	public DictionaryIndexer(Corpus origin) {
		super(origin);
	}

	@Override
	protected void indexCorpus() {
		for(Entry e: origin){
			indexEntry(e);
		}
	}

	@Override
	protected void castRawData(Object readObject) {
		this.dict = (HashMap<Integer, List<Long>>) readObject;
	}


	private void processSourceFile() {
		byte[] rawBytes = new byte[256];
		String sentence = "";
		try {
			RandomAccessFile file = new RandomAccessFile(this.fileName, "r");
			Long fileLength = file.length();
			for (Long i = 0l; i < fileLength; i+=256){
				file.seek(i);
				int bytesRead = file.read(rawBytes);
				for (int k =0; k < 256; k++){
					if ((int)rawBytes[k] <=0) { rawBytes[k] = (byte)' ';}
				}
				sentence = new String(rawBytes);
				sentence = sentence.replace('.', ' ').replace('\r',' ').trim();

				String[] words = sentence.split("\\s+");
				updateDict(words, i);

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("problem word: "+ sentence.split("\\s+"));
		}

		writeDictionaryToFile();
	}

	@Override
    protected void writeParams( ObjectOutputStream objectOut) throws IOException {
		super.writeParams(objectOut);
		objectOut.writeObject(dict);
	}
	private void writeDictionaryToFile() {
		try {

			FileOutputStream fileOut = new FileOutputStream(this.dictFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.hashCode);
			objectOut.writeObject(this.dict);
			objectOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void updateDict(String[] words, long i) {
		for (String word : words){
			word = STEMMER.stem(word.trim());

			if (Stopwords.isStemmedStopword(word) || word.equals("")){
				continue;
			}
			List<Long> curList = this.dict.computeIfAbsent(word.hashCode(), k -> new ArrayList<>());
			curList.add(i);
			//            System.out.print(word+" ");
		}
		//        System.out.println();
	}



	@Override
	protected String getIndexType() {
		return TYPE_NAME;
	}

	@Override
	public IQuerySearch asSearchInterface() {
		// TODO Auto-generated method stub
		return null;
	}



}
