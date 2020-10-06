package org.lombrozo.bunny.util;

import java.util.Random;

public class RandomString {

    private final String string;

    public RandomString() {
        this(105);
    }

    public RandomString(int size) {
        this.string = init(size);
    }

    private String init(int size) {
        int leftBorder = 48;
        int rightBorder = 122;
        return new Random().ints(size, leftBorder, rightBorder)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return string;
    }
}
