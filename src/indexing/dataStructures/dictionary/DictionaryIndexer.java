package indexing.dataStructures.dictionary;

import indexing.dataStructures.IdataStructure;
import textStructure.BlockResult;
import textStructure.Corpus;
import textStructure.WordResult;
import utils.Stemmer;
import utils.Stopwords;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static utils.MD5.getMd5;

public class DictionaryIndexer implements IdataStructure {
    private String dictFile;
    private String hashCode;
    private HashMap<Integer, List<Long>> dict = new HashMap<>();
    private String fileName;
    private static final Stemmer STEMMER = new Stemmer();
    private Corpus origin;

    public DictionaryIndexer(){

    }

    @Override
    public void indexFile(String fileName) {
        this.fileName = fileName;
        this.dictFile = this.fileName.substring(0, this.fileName.lastIndexOf('.')) + ".dictObj";
        this.hashCode = getMd5(this.fileName);
    }


    public DictionaryIndexer(String fileName){
        this.fileName = fileName;
        this.dictFile = this.fileName.substring(0, this.fileName.lastIndexOf('.')) + ".dictObj";
        this.hashCode = getMd5(this.fileName);
    }
    public void indexFile() {


        boolean retry = false;
        try {
            FileInputStream fi = new FileInputStream(new File(dictFile));
            ObjectInputStream oi = new ObjectInputStream(fi);
            String oldHashCode = (String) oi.readObject();
            HashMap<Integer, List<Long>> oldMap = (HashMap<Integer, List<Long>>) oi.readObject();
            if (oldHashCode.equals(this.hashCode)) {
                this.dict = oldMap;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            retry = true;
        }

        if (retry) {
            processSourceFile();
        }
    }

    private void processSourceFile() {
        byte[] rawBytes = new byte[256];
        String sentence = "";
        try {
            RandomAccessFile file = new RandomAccessFile(this.fileName, "r");
            Long fileLength = file.length();
            for (Long i = 0l; i < fileLength; i+=256){
                file.seek(i);
                int bytesRead = file.read(rawBytes);
                for (int k =0; k < 256; k++){
                    if ((int)rawBytes[k] <=0) { rawBytes[k] = (byte)' ';}
                }
                sentence = new String(rawBytes);
                sentence = sentence.replace('.', ' ').replace('\r',' ').strip();

                String[] words = sentence.split("\\s+");
                updateDict(words, i);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("problem word: "+ sentence.split("\\s+"));
        }

        writeDictionaryToFile();
    }

    private void writeDictionaryToFile() {
        try {

            FileOutputStream fileOut = new FileOutputStream(this.dictFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.hashCode);
            objectOut.writeObject(this.dict);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateDict(String[] words, long i) {
        for (String word : words){
            word = STEMMER.stem(word.strip());

            if (Stopwords.isStemmedStopword(word) | word.equals("")){
                continue;
            }
            List<Long> curList = this.dict.computeIfAbsent(word.hashCode(), k -> new ArrayList<>());
            curList.add(i);
//            System.out.print(word+" ");
        }
//        System.out.println();
    }
    @Override
    public List<WordResult> searchWord(String word) {
        return null;
    }

    @Override
    public List<BlockResult> searchWordList(Collection<String> wordList) {
        return null;
    }

    @Override
    public List<WordResult> searchMetaData(String word) {
        return null;
    }

    @Override
    public Corpus getOrigin() {
        return this.origin;
    }

    @Override
    public void indexCorpus(Corpus corpus) {

    }


}
