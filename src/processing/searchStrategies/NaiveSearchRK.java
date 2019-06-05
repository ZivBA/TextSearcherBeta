package processing.searchStrategies;

import processing.textStructure.Block;
import processing.textStructure.Corpus;
import processing.textStructure.WordResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class NaiveSearchRK extends NaiveSearch {
    public NaiveSearchRK(Corpus origin) {
        super(origin);
    }

	/**
	 * Override the naive search with this implementation of the RabinKarp search!
	 * @param query The query string to search for.
	 * @return  List of WordResults!
	 */
	@Override
	public List<WordResult> search(String query){
    	//TODO implement me!
    }

	/**
	 * Implementation of the RK search algorithm.
	 * @param pattern   The pattern to search for
	 * @param text      The text to search against.
	 * @return          Position of the pattern in the text or -1 if not found.
	 */
	private static int RabinKarpMethod(char[] pattern, char[] text) {
	    int patternSize = pattern.length;
	    int textSize = text.length;

	    long prime = getBiggerPrime(patternSize);

	    long r = 1;
	    for (int i = 0; i < patternSize - 1; i++) {
		    r *= 2;
		    r = r % prime;
	    }

	    long[] t = new long[textSize];
	    t[0] = 0;

	    long pfinger = 0;

	    for (int j = 0; j < patternSize; j++) {
		    t[0] = (2 * t[0] + text[j]) % prime;
		    pfinger = (2 * pfinger + pattern[j]) % prime;
	    }

	    int i = 0;
	    boolean passed = false;

	    int diff = textSize - patternSize;
	    for (i = 0; i <= diff; i++) {
		    if (t[i] == pfinger) {
			    passed = true;
			    for (int k = 0; k < patternSize; k++) {
				    if (text[i + k] != pattern[k]) {
					    passed = false;
					    break;
				    }
			    }

			    if (passed) {
				    return i;
			    }
		    }

		    if (i < diff) {
			    long value = 2 * (t[i] - r * text[i]) + text[i + patternSize];
			    t[i + 1] = ((value % prime) + prime) % prime;
		    }
	    }
	    return -1;

    }


    private static long getBiggerPrime(int m) {
        BigInteger prime = BigInteger.probablePrime(getNumberOfBits(m) + 1, new Random());
        return prime.longValue();
    }

    private static int getNumberOfBits(int number) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }
}
