package indexing.dataStructures.naive;

import processing.parsingRules.IparsingRule;
import processing.searchStrategies.NaiveSearchRK;
import indexing.textStructure.*;
import indexing.Aindexer;
import processing.searchStrategies.NaiveSearch;

public class NaiveIndexer extends Aindexer<NaiveSearch> {

	public static final String TYPE_NAME = "naive_indexer";
	private final boolean isRK;


	public NaiveIndexer(Corpus corpus, boolean RK){
		super(corpus);
		this.isRK = RK;
	}

	public NaiveIndexer(Corpus corpus) {
		super(corpus);
		this.isRK = false;
	}

	@Override
	protected void indexCorpus() {
		// does nothing
	}

	@Override
	protected void castRawData(Object readObject) {
		// does nothing
	}

	public Corpus getOrigin() {
		return this.origin;
	}


	@Override
	protected String getIndexType() {
		return TYPE_NAME;
	}

	@Override
	public IparsingRule getParseRule() {
		return this.origin.getParsingRule();
	}


	@Override
	public NaiveSearch asSearchInterface() {
		// TODO Auto-generated method stub
		return this.isRK ? new NaiveSearch(this.origin) : new NaiveSearchRK(this.origin);
	}


}
