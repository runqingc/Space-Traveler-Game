package edu.uchicago.gerber._07streams.E19_5;

import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("First 5 elements: " + StreamUtil.toString(integerStream, 5));

        Stream<String> stringStream = Stream.of("apple", "banana", "cherry", "date", "elderberry");
        System.out.println("First 3 elements: " + StreamUtil.toString(stringStream, 3));
    }
}
