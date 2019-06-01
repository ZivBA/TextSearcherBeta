package indexing.dataStructures.dictionary;

import indexing.Aindexer;
import indexing.dataStructures.IdataStructure;
import search.DictionarySearch;
import search.IQuerySearch;
import textStructure.Block;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static utils.MD5.getMd5;

public class DictionaryIndexer extends Aindexer<DictionarySearch> {
	private String dictFile;
	private String hashCode;
	private List<BlockDict> dicts;
	private String fileName;
	private static final Stemmer STEMMER = new Stemmer();
	public static final String TYPE_NAME = "dictionary_indexer";



	public DictionaryIndexer(Corpus origin) {
		super(origin);
		dicts = new LinkedList<>();
	}

	@Override
	protected void indexCorpus() {
		for(Entry e: origin){
			indexEntry(e);
		}
	}

	@Override
	protected void castRawData(Object readObject) {
		this.dicts = (List<BlockDict>) readObject;
	}
	
	public void indexEntry(Entry inputEntry) {
		for(Block b: inputEntry) {
			processBlock(b);
		}
				
	}

	private void processBlock(Block b) {
		BlockDict dict = new BlockDict(b);
		dicts.add(dict);
		String blockString = b.toString();
		String[] words = blockString.split("\\s+");
		long currIndex = 0;
		for(String word: words) {
			updateDict(dict,word, blockString.indexOf(word, (int)currIndex));
			currIndex += word.length();
		}
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
//				updateDict(words, i);

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
		objectOut.writeObject(dicts);
	}
	private void writeDictionaryToFile() {
		try {

			FileOutputStream fileOut = new FileOutputStream(this.dictFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.hashCode);
			objectOut.writeObject(this.dicts);
			objectOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void updateDict(BlockDict blockDict, String word, long i) {
		Map<Integer, List<Long>> dict = blockDict.getDict();
		word = STEMMER.stem(word.trim()); // trim should be a step inside the stemmer

		if (Stopwords.isStemmedStopword(word) || word.equals("")){
			return;
		}
		List<Long> curList = dict.computeIfAbsent(word.hashCode(), k -> new ArrayList<>());
		curList.add(i);

	}



	@Override
	protected String getIndexType() {
		return TYPE_NAME;
	}

	@Override
	public DictionarySearch asSearchInterface() {
		// TODO Auto-generated method stub
		return new DictionarySearch(dicts);
	}



}
