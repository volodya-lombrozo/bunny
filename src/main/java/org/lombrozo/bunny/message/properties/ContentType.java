package org.lombrozo.bunny.message.properties;

public class ContentType implements Property {

    private final String value;

    public ContentType(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.CONTENT_TYPE;
    }

    @Override
    public String value() {
        return value;
    }
}
