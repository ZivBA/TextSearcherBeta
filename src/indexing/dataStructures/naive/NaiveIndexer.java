package indexing.dataStructures.naive;

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


	public NaiveIndexer(Corpus corpus){
		super(corpus);
	}

	@Override
	protected void indexCorpus() {
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


	public void indexFile(String inputFile) {
		// naive indexer doesn't really index anything! muahahah...
	}


	@Override
	public NaiveSearch asSearchInterface() {
		// TODO Auto-generated method stub
		return new NaiveSearch(this.origin);
	}


}
