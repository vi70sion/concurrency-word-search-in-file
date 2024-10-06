package org.example;

public class Main {

    public static final String SEARCH_WORD = "nes";
    public static int totalWordCount = 0; // Bendras žodžių skaičius


    public static void main(String[] args) {

        String[] fileNames = {"C:\\JavaTest\\API\\concurrency-word-search-in-file\\src\\file1.txt",
                              "C:\\JavaTest\\API\\concurrency-word-search-in-file\\src\\file2.txt",
                              "C:\\JavaTest\\API\\concurrency-word-search-in-file\\src\\file3.txt"};

        Thread[] threads = new Thread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new Thread(new SearchWord(fileNames[i]),"Thread" + i);
            threads[i].start();
        }

        // Laukiame, kol visos gijos baigs darbą
        for (Thread thread : threads) {
            try {
                thread.join(); // Laukiame, kol gija baigs darbą
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Bendras žodžio '" + SEARCH_WORD + "' rastų kartų skaičius: " + totalWordCount);
    }

}