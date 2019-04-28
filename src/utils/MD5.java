package utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Java program to calculate utils.MD5 hash value
public class MD5 {
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing utils.MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest dataStructures
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMd5(byte[] inputBytes)
    {
        try {

            // Static getInstance method is called with hashing utils.MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(inputBytes);

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest dataStructures
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    // Driver code
//    public void main(String args[]) throws NoSuchAlgorithmException
//    {
//        String fileString = "GeeksForGeeks";
//
//        long startTime = 0;
//        long endTime = 0;
//        long duration = 0;
//        long sum = 0;
//        for (int i = 0; i < 1000; i++){
//            try {
//                fileString = new String(Files.readAllBytes(Paths.get("/cs/usr/zivben/IdeaProjects/MyClassLibrary/src/Shakespeare.txt")), StandardCharsets.UTF_8);
//                startTime = System.nanoTime();
////                System.out.println("Your HashCode Generated by utils.MD5 is: " + getMd5(fileString));
//                getMd5(fileString);
//                endTime = System.nanoTime();
//                duration = (endTime - startTime);
//                sum += duration;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        double seconds = (double)(sum / 1000f) / 1_000_000_000.0;
//        System.out.println("Average time: " + seconds);
//        System.out.println("Total time: " + (double) sum / 1_000_000_000.0);
//
//
//    }
} 