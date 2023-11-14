package edu.uchicago.gerber._07streams.E19_5;

import java.util.stream.Stream;
import java.util.stream.Collectors;

public class StreamUtil {

    public static <T> String toString(Stream<T> stream, int n) {
        return stream.limit(n)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
