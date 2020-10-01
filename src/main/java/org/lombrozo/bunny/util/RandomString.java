package org.lombrozo.bunny.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomString {

    private final int leftBorder = 48;
    private final int rightBorder = 122;
    private final String string;

    public RandomString() {
        this(15);
    }

    public RandomString(int size) {
        this.string = init(size);
    }

    private String init(int size) {
        return new Random().ints(size, leftBorder, rightBorder)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return string;
    }
}
