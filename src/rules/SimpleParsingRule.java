package rules;

import textStructure.Block;
import textStructure.LinesBlock;
import textStructure.QueryResult;

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

	private String readFileToString() {
		try {
			byte[] rawFile = new byte[Math.toIntExact(inputFile.length())];
			inputFile.readFully(rawFile);
			return new String(rawFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}


	@Override
	public int getWordDistance(QueryResult first, QueryResult second, String[] queryWords) {
		return 0;
	}

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {
		return new Block(inputFile, startPos, endPos);
	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {

		final Pattern p = Pattern.compile(getSplitRegex());
		final Matcher m = p.matcher("");

		List<Block> entryBlocks = new LinkedList<>();
		int rawChunkSize = MAXLINELENGTH * 15;
		byte[] rawBytes = new byte[rawChunkSize];
		String sentence = "";
		try {
			long curBlockStart = 0, curBlockEnd;
			boolean endBlock = false;
			Long lastIndex = inputFile.length();
			Long curIndex = 0L;
			for (Long i = curIndex; i < lastIndex; i += rawChunkSize) {
				this.inputFile.seek(i);
				int bytesRead = this.inputFile.read(rawBytes);
				m.reset(new String(rawBytes));
				while (m.find()) {
					if (!endBlock) {
						curBlockStart = i;
						endBlock = true;
					} else {
						curBlockEnd = i;
						entryBlocks.add(parseRawBlock(this.inputFile, curBlockStart, curBlockEnd));
					}
				}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryBlocks;

	}


	private String getSplitRegex() {
		return "((\\r?\\n){2})|(^\\r?\\n)";
	}



}
