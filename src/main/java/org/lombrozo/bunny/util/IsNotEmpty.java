package org.lombrozo.bunny.util;

public class IsNotEmpty {

    private final String value;

    public IsNotEmpty(String value) {
        this.value = value;
    }

    public boolean check() {
        return value != null && !value.isEmpty() && !value.trim().isEmpty();
    }
}
