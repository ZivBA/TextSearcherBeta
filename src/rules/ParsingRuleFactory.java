package rules;

import indexing.dataStructures.dictionary.DictionaryIndexer;
import indexing.dataStructures.naive.NaiveIndexer;

public class ParsingRuleFactory {
    public static IparsingRule createRuleByName(String name){
        if(name == null){
            throw new RuntimeException("no parsing rule given");
        }
        switch (name){
            case "simple":
                return new SimpleParsingRule();
            case "st_movies":
                return new STmovieParsingRule();
            case "st_tv":
                return new STtvSeriesParsingRule();
            default:
                throw new RuntimeException("invalid parsing rule name");
        }

    }
}
