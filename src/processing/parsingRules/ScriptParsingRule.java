package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ScriptParsingRule implements IparsingRule {
    @Override
    public int getWordDistance(WordResult first, WordResult second, String[] queryWords) {
        return 0;
    }

    protected abstract String getBlockStartRegex();

    protected abstract String getCharactersRegex();


    @Override
    public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {
//		System.out.println("start " + startPos + " end " + endPos);
        try {
            byte[] rawBytes = new byte[Math.toIntExact(endPos - startPos + 1)];
            inputFile.seek(startPos);
            inputFile.read(rawBytes);
            String scene = new String(rawBytes);
            // scene title
            Pattern titlePattern = Pattern.compile(getBlockStartRegex(), Pattern.MULTILINE);
            Matcher titleMatcher = titlePattern.matcher(scene);

            if (!titleMatcher.find()) {
                throw new UnsupportedOperationException("Just a temporary exception since this cannot happen");
            }

            List<String> metaData = new LinkedList<>();

            // scene charachters
            Pattern charachterPattern = Pattern.compile(getCharactersRegex(), Pattern.MULTILINE);
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
        final Pattern p = Pattern.compile(getBlockStartRegex(), Pattern.MULTILINE);
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
        for(int i=0; i< 256; i++){
            System.out.print("=");
        }
        System.out.println();

        System.out.println(wordResult.toString());
        Pattern resultPat = Pattern.compile(getMatcherRegex(wordResult.getWord()), Pattern.DOTALL);
        Matcher m = resultPat.matcher(wordResult.getBlock().toString());
        List<String> meta = wordResult.getBlock().getMeta();
        System.out.println("Appearing in scene " + meta.get(1) + ", titled \"" + meta.get(0) + "\"");
//		System.out.println("Taken out of the entry " + "\"" + entryName + "\"");
        if(!meta.get(2).equals(""))
            System.out.println("With the characters: " + meta.get(2));
//		System.out.println("Written by: " + "\"" + author + "\"");

        //System.out.println(m.find() ? m.group() : "ERROR");
//		long[] offsets = wordResult.getOffsets();
//		for(long l: offsets){
//			System.out.println("words from block " + wordResult.getBlock().extractFromBlock(l,l+20) + "\n");
//
//		}
//		System.out.println("In the scene with the metadata: " + wordResult.getBlock().getMeta() + "\n");
    }

}
