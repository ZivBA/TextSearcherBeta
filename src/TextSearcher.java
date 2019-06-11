import dataStructures.Aindexer;
import dataStructures.IndexFactory;
import processing.textStructure.Corpus;
import processing.textStructure.WordResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main program - A text searching module that indexes and queries large corpuses for strings or word groups
 */
public class TextSearcher {
    public static final String ERROR = "ERROR";
    private static final String CORPUS_KEY = "CORPUS";
    private static final String INDEXER_KEY = "INDEXER";
    private static final String PARSING_RULE_KEY = "PARSE_RULE";
    private static final String QUERY_KEY = "QUERY";
    private static Aindexer sIndexer;
    private static String sQuery;

    /**
     * Main method. Reads and parses a command file and if a query exists, prints the results.
     * @param args
     */
    public static void main(String[] args) {
        if(args.length != 1){
            handleError(new Exception("Usage: TextSearcher configuration_file"));
        }
	    List<? extends WordResult> results = null;
        try{
            Map<String,String> configuration = parseConfiguration(args[0]);
            readConfiguration(configuration);
            sIndexer.index();

            if(sQuery!=null) {
                results = sIndexer.asSearchInterface().search(sQuery);
            }
        }catch (Exception e){
            handleError(e);
        }
        if (results != null){
            for (int i =0; i < results.size(); i++){
                try {
                    sIndexer.getParseRule().printResult(results.get(i));
                } catch (IOException | IndexOutOfBoundsException e) {
                    break;
                }
            }
	        System.out.println("You got "+ results.size() + " results! congratulations.");
        }

    }

    private static void readConfiguration(Map<String, String> configuration) throws IOException {

        if(configuration.get(CORPUS_KEY)==null){
            throw new RuntimeException("No corpus given");
        }

        Corpus corpus = new Corpus(configuration.get(CORPUS_KEY),configuration.get(PARSING_RULE_KEY));
        sIndexer = IndexFactory.createIndexerByName(configuration.get(INDEXER_KEY),corpus);
        sQuery = configuration.get(QUERY_KEY);
    }

    private static void handleError(Exception e) {
        throw new RuntimeException(e);
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
                            throw new RuntimeException("bad configuration value for key" + line);
                        }
                        configuration.put(line, value);
                        break;
                    default:
                        throw new RuntimeException("bad configuration key " + line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return configuration;
    }
}
