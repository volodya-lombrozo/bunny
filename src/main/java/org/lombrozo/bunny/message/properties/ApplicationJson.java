package org.lombrozo.bunny.message.properties;

public class ApplicationJson implements Property {

    private final Property property = new ContentType("application/json");

    @Override
    public PropertyKey key() {
        return property.key();
    }

    @Override
    public String value() {
        return property.value();
    }
}
