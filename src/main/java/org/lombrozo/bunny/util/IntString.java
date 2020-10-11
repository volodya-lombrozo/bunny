package org.lombrozo.bunny.util;

public class IntString {

    private final Integer value;

    public IntString(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
