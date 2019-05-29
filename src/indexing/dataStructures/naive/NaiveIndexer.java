package indexing.dataStructures.naive;

import search.NaiveSearchRK;
import textStructure.*;
import indexing.Aindexer;
import indexing.dataStructures.IdataStructure;
import search.IQuerySearch;
import search.NaiveSearch;
import textStructure.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


	public List<BlockResult> searchWordList(Collection<String> wordList) {
		//TODO implement
		//implementation here
		return null;
	}


	public Corpus getOrigin() {
		return this.origin;
	}


	@Override
	protected String getIndexType() {
		return TYPE_NAME;
	}



	@Override
	public NaiveSearch asSearchInterface() {
		// TODO Auto-generated method stub
		return this.isRK ? new NaiveSearch(this.origin) : new NaiveSearchRK(this.origin);
	}


}
