package org.lombrozo.bunny.message;

public class EmptyProperties implements Properties {
    @Override
    public String property(PropertyKey key) {
        return "";
    }

    @Override
    public void put(Property property) {
    }
}
