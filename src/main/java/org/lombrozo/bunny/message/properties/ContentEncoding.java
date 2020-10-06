package org.lombrozo.bunny.message.properties;


public class ContentEncoding implements Property {

    private final String value;

    public ContentEncoding(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.CONTENT_ENCODING;
    }

    @Override
    public String value() {
        return value;
    }
}
