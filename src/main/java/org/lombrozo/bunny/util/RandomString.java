package org.lombrozo.bunny.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomString {

    private final String string;


    public RandomString() {
        this.string = init();
    }

    private String init() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return string;
    }
}
