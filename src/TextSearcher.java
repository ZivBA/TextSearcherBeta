public class TextSearcher {
    private static String corpusLocation, query, indexStrategy;

    public static void main(String[] args) {
        if(args.length > 3){
            throw new RuntimeException("Usage: TextSearcher corpusPath indexStrategies ?query");
        }
        corpusLocation = args[0];
        indexStrategy = args[1];
        if(args.length == 3){
            query = args[2];
        }


    }
}
