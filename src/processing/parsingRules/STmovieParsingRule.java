package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class STmovieParsingRule implements IparsingRule {
	static final private String START_BLOCK_REGEX = "^\\s{5,15}([0-9]+)(.*)\\1";
	static final private String CHARACHTOR_NAMES = "^[^\\S\\r\\n]+([A-Z]+)[^\\S\\r\\n]*\\n";
	static final private Pattern charachterPattern = Pattern.compile(CHARACHTOR_NAMES);


	public STmovieParsingRule() throws IOException {
	}

//	public Block parseBlock(RandomAccessFile inputFile, long startIdx) throws IOException {
//		//TODO implement regex parsing using the following processing.parsingRules:
//        /*
//            each scene starts with a line like "               1   BLACK                                                        1"
//            where the first and last numbers represent the scene number.
//            within a scene there are lines like "                   Absolute quiet. SOUND bleeds in. Low level b.g."
//            which are descriptions and are not interesting except for indexing words
//            there are lines like: "                                           SAAVIK'S VOICE"
//            where there are only upper case letters, describing who is speaking and should be stored within the
//            block MD as charachters within the scene.
//            lines less indented but also only uppercase like "                   INT. ENTERPRISE BRIDGE"
//            are scene locations and should also be stored in MD as locations in scene
//            lines like "                             Leaving Section Fourteen for"
//            are less indented than scene description, and represent dialog.
//
//            The indexer must store, efficiently, the characters' names and scene locations within the block MD for pretty printing the results.
//
//         */
//		return null;
//
//	}

	@Override
	public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
		// this should actually be a comparator for wordResults, where two word results are considered equal if they come from the same block
		// if they come from different blocks, then either both blocks contain all the query words, in which case the order is according to scene number
		// otherwise, whichever block has more queryWords gets a higher score (is grater than the other)
		return -1;
	}

	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {
//		System.out.println("start " + startPos + " end " + endPos);
		try {
			byte[] rawBytes = new byte[Math.toIntExact(endPos - startPos + 1)];
			inputFile.seek(startPos);
			inputFile.read(rawBytes);
			String scene = new String(rawBytes);
			// scene title
			Pattern titlePattern = Pattern.compile(START_BLOCK_REGEX, Pattern.MULTILINE);
			Matcher titleMatcher = titlePattern.matcher(scene);

			if (!titleMatcher.find()) {
				throw new UnsupportedOperationException("Just a temporary exception since this cannot happen");
			}

			List<String> metaData = new LinkedList<>();

			// scene charachters
			Pattern charachterPattern = Pattern.compile(CHARACHTOR_NAMES, Pattern.MULTILINE);
			Matcher charachterMatcher = charachterPattern.matcher(scene);

			metaData.add(titleMatcher.group(2).trim());
			metaData.add(titleMatcher.group(1).trim());

			List<String> characters = new LinkedList<>();
			while (charachterMatcher.find()) {
				String charName = scene.substring(charachterMatcher.start(), charachterMatcher.end()).trim();
				if (!characters.contains(charName)) {
					characters.add(charName);
				}
			}
			metaData.add(String.join(",",characters));
			Block resBlock = new Block(inputFile, startPos, endPos);
			resBlock.setMetadata(metaData);

			return resBlock;

		} catch (IOException e) {
			e.printStackTrace();
			return null; //TODO change this behavior
		}

	}

	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {
		final Pattern p = Pattern.compile(START_BLOCK_REGEX, Pattern.MULTILINE);
		final Matcher m = p.matcher("");
		List<Block> entryBlocks = new LinkedList<>();
		int rawChunkSize = MAXLINELENGTH * 15;
		byte[] rawBytes = new byte[rawChunkSize];
		String sentence = "";
		try {
			final long fileLength = inputFile.length();
			long curBlockStart = 0, curBlockEnd;
			boolean endBlock = false;
			Long curIndex = 0L;
			for (Long i = curIndex; i < fileLength; i += rawChunkSize) {
				inputFile.seek(i);
				int bytesRead = inputFile.read(rawBytes);
				m.reset(new String(rawBytes));
				while (m.find()) {
					if (!endBlock) {
						curBlockStart = m.start() + i;
						endBlock = true;
					} else {
						curBlockEnd = Math.min(m.start() + i,fileLength);
						if(curBlockEnd >= fileLength) break;
						entryBlocks.add(parseRawBlock(inputFile, curBlockStart, curBlockEnd));
						curBlockStart = m.start() + i;
					}
				}


			}
			if (endBlock) {
				entryBlocks.add(parseRawBlock(inputFile, curBlockStart, fileLength));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryBlocks;
	}

	@Override
	public void printResult(WordResult wordResult) throws IOException {
		Pattern resultPat = Pattern.compile(getMatcherRegex(wordResult.getWord()), Pattern.DOTALL);
		Matcher m = resultPat.matcher(wordResult.getBlock().toString());

		System.out.println("The query was matched at the line: ");
		System.out.println(wordResult.toString());
		//System.out.println(m.find() ? m.group() : "ERROR");
		long[] offsets = wordResult.getOffsets();
		for(long l: offsets){
			System.out.println("words from block " + wordResult.getBlock().extractFromBlock(l,l+20) + "\n");

		}
		System.out.println("In the scene with the metadata: " + wordResult.getBlock().getMeta() + "\n");
	}
}