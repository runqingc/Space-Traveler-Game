package edu.uchicago.gerber._07streams.E19_14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class PalindromeFinder {
    public static void findPalindrome(String fileName) {
        try {
            // Read all lines from the file into an ArrayList
            ArrayList<String> words = new ArrayList<>(Files.readAllLines(Paths.get(fileName)));

            // Find any palindrome with at least five letters
            Optional<String> palindrome = words.parallelStream()
                    .filter(w -> w.length() >= 5)
                    .filter(PalindromeFinder::isPalindrome)
                    .findAny();

            // Print the found palindrome, if present
            palindrome.ifPresent(System.out::println);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean isPalindrome(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

}
