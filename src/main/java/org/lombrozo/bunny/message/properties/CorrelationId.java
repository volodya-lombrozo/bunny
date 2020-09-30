package org.lombrozo.bunny.message.properties;

import org.lombrozo.bunny.util.RandomString;

public class CorrelationId implements Property {

    private final String id;

    public CorrelationId() {
        this(new RandomString().toString());
    }

    public CorrelationId(String id) {
        this.id = id;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.CORRELATION_ID;
    }

    @Override
    public String value() {
        return id;
    }

    @Override
    public String toString() {
        return "CorrelationId{" + id + "}";
    }
}
