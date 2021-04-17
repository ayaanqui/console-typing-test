package com.github.ayaanqui.consoleTypingTest;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class App {
    private static String filePath = "./src/main/resources/";
    private static Random r = new Random();

    private static Set<String> addWordsFromFiles(String... files) {
        Set<String> allWords = new HashSet<>();

        Arrays.stream(files).forEach(file -> {
            try {
                Scanner s = new Scanner(new File(filePath + file));
                while (s.hasNext())
                    allWords.add(s.next());
                s.close();
            } catch (Exception e) {
            }
        });
        return allWords;
    }

    private static int getRandom(int range) {
        return r.nextInt(range);
    }

    private static String generateParagraph(Set<String> words, int minSize, int maxSize, boolean specailChars,
            int size) {
        Stream<String> reducedWords = words.stream().filter(w -> {
            return w.length() >= minSize && w.length() <= maxSize;
        });

        Object[] reducedWordsArray = reducedWords.toArray();
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < size; i++) {
            paragraph.append(reducedWordsArray[getRandom(reducedWordsArray.length)] + " ");
        }
        return paragraph.toString();
    }

    public static void main(String[] args) {
        System.out.println("**Typing Test Program**");

        Set<String> words = addWordsFromFiles("words_alpha.txt", "words.txt", "words_english.txt");
        System.out.println("Total words loaded: " + words.size() + "\n");

        System.out.println(generateParagraph(words, 3, 8, false, 50));
    }
}