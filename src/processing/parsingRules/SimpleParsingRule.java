package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleParsingRule implements IparsingRule{

	private final RandomAccessFile inputFile;


	public SimpleParsingRule(RandomAccessFile file) {
		inputFile = file;
    }

	@Override
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
		return 0;
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
				this.inputFile.seek(i);
				int bytesRead = this.inputFile.read(rawBytes);
				String rawBlock = new String(rawBytes);
				m.reset(rawBlock);
				while (m.find()) {
					if (m.end()-m.start() > 5) {
						entryBlocks.add(parseRawBlock(this.inputFile, m.start() + i, m.end() + i));
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
		return "(.*\\n\\n){1,5}";
	}



}
