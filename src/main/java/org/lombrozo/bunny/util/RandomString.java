package org.lombrozo.bunny.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomString {

    private final String string;

    public RandomString() {
        this(7);
    }

    public RandomString(int size) {
        this.string = init(size);
    }

    private String init(int size) {
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return string;
    }
}
