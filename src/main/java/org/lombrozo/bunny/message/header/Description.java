package org.lombrozo.bunny.message.header;


public class Description implements Header {
    private final String value;

    public Description(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return "description";
    }

    @Override
    public String value() {
        return value;
    }
}
