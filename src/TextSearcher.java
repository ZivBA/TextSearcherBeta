import indexing.Aindexer;
import indexing.IndexFactory;
import indexing.dataStructures.naive.NaiveIndexer;
import rules.IparsingRule;
import rules.ParsingRuleFactory;
import textStructure.Corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextSearcher {
    public static final String ERROR = "ERROR";
    private static String corpusLocation, query, indexStrategy;
    private static final String CORPUS_KEY = "CORPUS";
    private static final String INDEXER_KEY = "INDEXER";
    private static final String PARSING_RULE_KEY = "PARSING_RULE";
    private static final String QUERY_KEY = "QUERY";
    private static Aindexer sIndexer;
    private static Corpus sCorpus;
    private static String sQuery;
    private static IparsingRule sParsingRule;
    public static void main(String[] args) {
        if(args.length != 1){
            handleError("Usage: TextSearcher configuration_file");
        }
        Map<String,String> configuration = parseConfiguration(args[0]);
        readConfiguration(configuration);
	sIndexer.index();
    }

    private static void readConfiguration(Map<String, String> configuration) {
        if(configuration.get(CORPUS_KEY)==null){
            handleError("No corpus given");
        }
        Corpus corpus = new Corpus(configuration.get(CORPUS_KEY));

        sParsingRule = ParsingRuleFactory.createRuleByName(configuration.get(PARSING_RULE_KEY));
        sIndexer = IndexFactory.createIndexerByName(configuration.get(INDEXER_KEY),corpus,sParsingRule);

        sQuery = configuration.get(QUERY_KEY);
    }

    private static void handleError(String err) {
        throw new RuntimeException(err);
    }

    private static Map<String, String> parseConfiguration(String configurationPath) {
        Map<String,String> configuration = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    configurationPath));
            String line = reader.readLine();
            while (line != null) {
                switch (line){
                    case CORPUS_KEY:
                    case INDEXER_KEY:
                    case PARSING_RULE_KEY:
                    case QUERY_KEY:
                        String value = reader.readLine();
                        if(value==null){
                            handleError("bad configuration value " + line);
                        }
                        configuration.put(line, value);
                        break;
                    default:
                        handleError("bad configuration key " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            handleError(e.getMessage());
        }
        return configuration;
    }
}
