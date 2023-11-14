package edu.uchicago.gerber._07streams.E19_7;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformString {
    public static void main(String[] args) {
        String input = "Hello world this is a test of the lambda function";

        Function<String, String> transform =
                s -> s.length() > 1 ? s.charAt(0) + "..." + s.charAt(s.length() - 1) : s;

        String result = Arrays.stream(input.split("\\s+"))
                .filter(s -> s.length() > 1)
                .map(transform)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
