package indexing.dataStructures.naiveSearch;

import textStructure.Block;
import textStructure.Corpus;
import textStructure.WordResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class NaiveSearchRK extends NaiveSearch {
    public NaiveSearchRK(Corpus corpus) {
        super(corpus);
    }

    private void searchBlock(Block blk, List<WordResult> results, String word) {
        char[] pattern = word.toCharArray();
        char[] text = blk.asString().toCharArray();

        int patternSize = pattern.length;
        int textSize = text.length;

        long prime = getBiggerPrime(patternSize);

        long base = 1;
        for (int i = 0; i < patternSize - 1; i++) {
            base *= 2;
            base = base % prime;
        }

        long[] rolLHashArr = new long[textSize];
        rolLHashArr[0] = 0;

        long pattenFP = 0;

        for (int j = 0; j < patternSize; j++) {
            rolLHashArr[0] = (2 * rolLHashArr[0] + text[j]) % prime;
            pattenFP = (2 * pattenFP + pattern[j]) % prime;
        }

        int i = 0;
        boolean passed = false;

        int diff = textSize - patternSize;
        for (i = 0; i <= diff; i++) {
            if (rolLHashArr[i] == pattenFP) {
                passed = true;
                for (int k = 0; k < patternSize; k++) {
                    if (text[i + k] != pattern[k]) {
                        passed = false;
                        break;
                    }
                }

                if (passed) {
                    results.add(new WordResult(blk, word, i));
                }
            }

            if (i < diff) {
                long value = 2 * (rolLHashArr[i] - base * text[i]) + text[i + patternSize];
                rolLHashArr[i + 1] = ((value % prime) + prime) % prime;
            }
        }

    }


    private static long getBiggerPrime(int m) {
        BigInteger prime = BigInteger.probablePrime(getNumberOfBits(m) + 1, new Random());
        return prime.longValue();
    }

    private static int getNumberOfBits(int number) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }

}
