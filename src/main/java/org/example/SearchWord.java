package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchWord implements Runnable{

    private String fileName;

    public SearchWord (String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int wordCount = searchWordInFile(fileName);
        synchronized (Main.class) {
            Main.totalWordCount += wordCount;
        }
    }

    public int searchWordInFile(String fileName) {
        int wordCount = 0;
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] words = content.split("\\W+"); // Skiria žodžius pagal nesimbolius
            for (String word : words) {
                if (word.equalsIgnoreCase(Main.SEARCH_WORD)) {
                    wordCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Klaida skaitant failą: " + fileName);
        }

        System.out.println("Žodis '" + Main.SEARCH_WORD + "' rastas faile " + fileName + ": " + wordCount + " kartus");
        return wordCount;
    }

}
