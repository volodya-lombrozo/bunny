package org.lombrozo.bunny.message.header;

public class From implements Header {

    private final String value;

    public From(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return "from";
    }

    @Override
    public String value() {
        return value;
    }
}
