package org.lombrozo.bunny.message.header;

public class StringHeader implements Header {

    private final String key;
    private final String value;

    public StringHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }
}
