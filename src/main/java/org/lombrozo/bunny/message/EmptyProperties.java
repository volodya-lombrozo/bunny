package org.lombrozo.bunny.message;

public class EmptyProperties implements Properties {
    @Override
    public String property(String key) {
        return "";
    }
}
