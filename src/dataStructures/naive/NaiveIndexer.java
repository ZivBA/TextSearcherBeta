package dataStructures.naive;

import dataStructures.Aindexer;
import processing.parsingRules.IparsingRule;
import processing.searchStrategies.NaiveSearch;
import processing.searchStrategies.NaiveSearchRK;
import processing.textStructure.Corpus;
import utils.WrongMD5ChecksumException;

import java.io.FileNotFoundException;

/**
 * A "naive" indexer. This approach forgoes actually preprocessing the file, and simply loads the text and searches directly on it.
 */
public class NaiveIndexer extends Aindexer<NaiveSearch> {

	public static final IndexTypes TYPE_NAME = IndexTypes.NAIVE;
	private final NaiveSearch searchStratagy;

	/**
	 * Basic constructor
	 * @param corpus    The corpus to search over
	 * @param RK        Whether or not to use Rabin-Karp search strategy
	 */
	public NaiveIndexer(Corpus corpus, boolean RK){
		super(corpus);
		this.searchStratagy = RK? new NaiveSearchRK(this.origin) : new NaiveSearch(this.origin);
	}


	public NaiveIndexer(Corpus corpus) {
		this(corpus, false);
	}

	@Override
	protected void indexCorpus() {
		// does nothing
	}

	@Override
	protected void readIndexedFile() throws FileNotFoundException, WrongMD5ChecksumException {
		// does nothing
	}

	@Override
	protected void castRawData(Object readObject) {
		// does nothing
	}

	@Override
	protected void writeIndexFile() {
		// does nothing
	}

	/**
	 * Get the source Corpus of this indexer
	 * @return
	 */
	public Corpus getOrigin() {
		return this.origin;
	}


	@Override
	protected IndexTypes getIndexType() {
		return TYPE_NAME;
	}

	@Override
	public IparsingRule getParseRule() {
		return this.origin.getParsingRule();
	}


	@Override
	public NaiveSearch asSearchInterface() {
		return this.searchStratagy;
	}


}
