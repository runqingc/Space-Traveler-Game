package edu.uchicago.gerber._07streams.E19_16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordGrouping {
    public static void printAverageWordLengthByFirstLetter(String fileName) {
        try {
            Map<Character, Double> averageWordLengths = Files.lines(Paths.get(fileName))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(
                            word -> word.charAt(0),
                            Collectors.averagingInt(String::length)
                    ));

            averageWordLengths.forEach((letter, avgLength) ->
                    System.out.println(letter + ": " + avgLength));

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
