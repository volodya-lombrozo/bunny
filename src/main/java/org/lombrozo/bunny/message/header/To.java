package org.lombrozo.bunny.message.header;

public class To implements Header {
    private final String value;

    public To(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return "to";
    }

    @Override
    public String value() {
        return value;
    }
}
