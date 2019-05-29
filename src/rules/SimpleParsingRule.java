package rules;

import textStructure.Block;
import textStructure.LinesBlock;
import textStructure.QueryResult;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleParsingRule extends LineParsingRule {

    public SimpleParsingRule(RandomAccessFile file) {
        super(file);
		inputFile = file;
		nextIndex = 0;
		fileAsString = readFileToString();
		lines = fileAsString.split(getSplitRegex());
		System.out.print(lines.length);
		for(String line : lines) {
			System.out.println(line);
			System.out.println("___");
		}
		currLine = 0;
    }

    @Override
    public int getWordDistance(QueryResult first, QueryResult second, String[] queryWords) {
        return 0;
    }


    
	@Override
	protected String getSplitRegex() {
		return "((\\r?\\n){2})|(^\\r?\\n)";
	}

	@Override
	protected boolean isStartOfBlock(String line) {
		return !line.trim().equals("");
	}
	
	@Override
    protected void setBlockEndIndex(int lineStartIndex, LinesBlock b) {
    	
		long end = b.getStartIndex() + lines[lineStartIndex].length();
        		
		b.setLineEnd(lineStartIndex + 1).setEnd(end);
		
	}

}
