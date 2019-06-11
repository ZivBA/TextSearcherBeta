package processing.parsingRules;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ParsingRuleFactory {
    public static IparsingRule createRuleByName(String name) throws IOException {
        if(name == null){
            throw new RuntimeException("no parsing rule given");
        }
        switch (name){
            case "SIMPLE":
                return new SimpleParsingRule();
            case "ST_MOVIE":
                return new STmovieParsingRule();
            case "ST_TV":
                return new STtvSeriesParsingRule();
            default:
                throw new RuntimeException("invalid parsing rule name");
        }

    }
}
