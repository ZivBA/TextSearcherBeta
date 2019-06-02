package indexing.dataStructures.dictionary;

import indexing.Aindexer;
import processing.parsingRules.IparsingRule;
import processing.searchStrategies.DictionarySearch;
import indexing.textStructure.*;
import processing.utils.Stemmer;
import processing.utils.Stopwords;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static processing.utils.MD5.getMd5;

public class DictionaryIndexer extends Aindexer<DictionarySearch> {
	private String dictFile;
	private String hashCode;
	private String fileName;
	private static final Stemmer STEMMER = new Stemmer();
	public static final String TYPE_NAME = "dictionary_indexer";
	private HashMap<Integer, List<Word>> dict;


	public DictionaryIndexer(Corpus origin) {
		super(origin);
		dict = new HashMap<>();
	}

	@Override
	protected void indexCorpus() {
		for(Entry e: origin){
			indexEntry(e);
		}
	}

	@Override
	protected void castRawData(Object readObject) {
		this.dict = (HashMap<Integer, List<Word>>) readObject;
	}


	public void indexEntry(Entry inputEntry) {
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher("");
		int chunkSize = 2048;
		for (Block blk : inputEntry){
			byte[] rawBytes = new byte[chunkSize];
			String sentence = "";

			try {
				RandomAccessFile file = blk.getRAF();

				Long fileLength = file.length();
				for (long i = blk.getStartIndex(); i < blk.getEndIndex(); i+=chunkSize){
					long blkOffset = i - blk.getStartIndex();
					file.seek(i);
					int bytesRead = file.read(rawBytes);
//					for (int k =0; k < 256; k++){
//						if ((int)rawBytes[k] <=0) { rawBytes[k] = (byte)' ';}
//					}
					sentence = new String(rawBytes);
					m.reset(sentence);
					long lastMatch = 0;
					while (m.find()){
						String word = sentence.substring(m.start(),m.end());
						updateDict(word, m.start()+blkOffset, m.end()+blkOffset, blk);
						lastMatch = m.end();
					}
					i -= (chunkSize-lastMatch);

				}

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("problem with line: "+ sentence);
			}
		}


	}


	@Override
    protected void writeParams( ObjectOutputStream objectOut) throws IOException {
		super.writeParams(objectOut);
		objectOut.writeObject(dict);
	}

	@Override
	public IparsingRule getParseRule() {
		return origin.getParsingRule();
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

	private void updateDict(String word, long start, long end, Block blk) {
		word = STEMMER.stem(word);
		if (Stopwords.isStemmedStopword(word)){
			return;
		}
		if (this.dict.containsKey(word.hashCode())){
			this.dict.get(word.hashCode()).add(new Word(blk,start, end));
		}else{
			this.dict.put(word.hashCode(), new LinkedList<>(List.of(new Word(blk,start, end))));
		}
	}



	@Override
	protected String getIndexType() {
		return TYPE_NAME;
	}

	@Override
	public DictionarySearch asSearchInterface() {
		// TODO Auto-generated method stub
		return new DictionarySearch(dict);
	}



}
