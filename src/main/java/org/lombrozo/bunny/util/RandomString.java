package org.lombrozo.bunny.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomString {

    private final String string;

    public RandomString() {
        this(35);
    }

    public RandomString(int size) {
        this.string = init(size);
    }

    private String init(int size) {
        int leftBorder = 48;
        int rightBorder = 122;
        return new Random().ints(size, leftBorder, rightBorder)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return string;
    }
}
