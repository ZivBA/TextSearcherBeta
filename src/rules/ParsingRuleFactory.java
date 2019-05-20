package rules;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ParsingRuleFactory {
    public static AparsingRule createRuleByName(String name, String filePath) throws FileNotFoundException {
        if(name == null){
            throw new RuntimeException("no parsing rule given");
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath ,"r");
        switch (name){
            case "simple":
                return new SimpleParsingRule(randomAccessFile);
            case "st_movies":
                return new STmovieParsingRule(randomAccessFile);
            case "st_tv":
                return new STtvSeriesParsingRule(randomAccessFile);
            default:
                throw new RuntimeException("invalid parsing rule name");
        }

    }
}
