package dataStructures.dictionary;

import dataStructures.Aindexer;
import processing.parsingRules.IparsingRule;
import processing.searchStrategies.DictionarySearch;
import processing.textStructure.Corpus;
import utils.WrongMD5ChecksumException;

import java.io.FileNotFoundException;

/**
 * An implementation of the abstract Aindexer class, backed by a simple hashmap to store words and their
 * locations within the files.
 */
public class DictionaryIndexer extends Aindexer<DictionarySearch> {

	/**
	 * Basic constructor, sets origin Corpus and initializes backing hashmap
	 * @param origin    the Corpus to be indexed by this DS.
	 */
	public DictionaryIndexer(Corpus origin) {

	}

	@Override
	public DictionarySearch asSearchInterface() {
		return null;
	}

	@Override
	protected void indexCorpus() {

	}

	@Override
	protected void readIndexedFile() throws FileNotFoundException, WrongMD5ChecksumException {

	}

	@Override
	protected void castRawData(Object readObject) {

	}

	@Override
	protected void writeIndexFile() {

	}

	@Override
	public IparsingRule getParseRule() {
		return null;
	}

	@Override
	protected IndexTypes getIndexType() {
		return null;
	}


}
