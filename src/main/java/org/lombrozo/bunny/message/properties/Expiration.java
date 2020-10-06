package org.lombrozo.bunny.message.properties;

public class Expiration implements Property {

    private final String expiration;

    public Expiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.EXPIRATION;
    }

    @Override
    public String value() {
        return expiration;
    }
}
