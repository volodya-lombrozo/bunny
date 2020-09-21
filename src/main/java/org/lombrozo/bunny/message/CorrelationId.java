package org.lombrozo.bunny.message;

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
    public String key() {
        return "correlation_id";
    }

    @Override
    public String value() {
        return id;
    }
}
