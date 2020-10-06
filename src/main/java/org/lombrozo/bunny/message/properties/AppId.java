package org.lombrozo.bunny.message.properties;

public class AppId implements Property {

    private final String value;

    public AppId(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.APP_ID;
    }

    @Override
    public String value() {
        return value;
    }
}
