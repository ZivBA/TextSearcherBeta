package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a basic parsing rule which splits a text file to blocks of lines.
 * It will try to get anywhere between 1 and 10 lines of text, as long as the last line it grabs is an empty line.
 */
public class SimpleParsingRule implements IparsingRule{


	/**
	 * A distance measure between two words according to this parsing rule. Will return the distance between the two words
	 * @param first     First WordResult object
	 * @param second    Second WordResult object
	 * @param queryWords    The actual query sent to the indexer
	 * @return
	 */
	@Override
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
		//TODO implement me!

	}

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {

		return new Block(inputFile, startPos, endPos);
	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {

		final Pattern p = Pattern.compile(getSplitRegex(), Pattern.DOTALL);
		final Matcher m = p.matcher("");

		List<Block> entryBlocks = new LinkedList<>();
		int rawChunkSize = MAXLINELENGTH * 15;
		byte[] rawBytes = new byte[rawChunkSize];
		String sentence = "";
		try {
			long endOfBlockOffset = 0, curBlockEnd;
			Long lastIndex = inputFile.length();
			for (long i = endOfBlockOffset; i < lastIndex-rawChunkSize; i += rawChunkSize) {
				inputFile.seek(i);
				int bytesRead = inputFile.read(rawBytes);
				String rawBlock = new String(rawBytes);
				m.reset(rawBlock);
				while (m.find()) {
					if (m.end()-m.start() > 5) {
						entryBlocks.add(parseRawBlock(inputFile, m.start() + i, m.end() + i));
					}
					endOfBlockOffset = m.end();
				}
				i -= (rawChunkSize - endOfBlockOffset);


			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryBlocks;

	}

	@Override
	public void printResult(WordResult wordResult) throws IOException {
		System.out.println("The result: \n" +wordResult.resultToString());
		System.out.println("Appears in the file: "+wordResult.getSourceEntry());
	}

	private String getSplitRegex() {
		return "(.*\\n\\n){1,10}";
	}



}
