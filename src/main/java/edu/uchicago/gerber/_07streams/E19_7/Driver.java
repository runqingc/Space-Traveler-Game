package edu.uchicago.gerber._07streams.E19_7;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lambda expression to transform the string
        Function<String, String> transform =
                s -> s.length() > 1 ? s.charAt(0) + "..." + s.charAt(s.length() - 1) : s;

        System.out.println("Enter a sentence (type 'exit' to quit):");

        // Continuously read lines from the console until 'exit' is typed
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Break the loop if the user types 'exit'
            if ("exit".equalsIgnoreCase(line.trim())) {
                break;
            }

            // Apply transformation and print results
            String result = Stream.of(line.split("\\s+"))
                    .filter(s -> s.length() > 1)
                    .map(transform)
                    .reduce((a, b) -> a + " " + b)
                    .orElse("");

            System.out.println(result);
            System.out.println("\nEnter another sentence (type 'exit' to quit):");
        }

        scanner.close();
    }
}
