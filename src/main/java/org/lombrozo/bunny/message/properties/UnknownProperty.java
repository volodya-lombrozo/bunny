package org.lombrozo.bunny.message.properties;

public class UnknownProperty implements Property {

    private final String value;

    public UnknownProperty(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.UNKNOWN;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }
}
