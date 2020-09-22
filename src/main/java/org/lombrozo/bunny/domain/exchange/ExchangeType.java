package org.lombrozo.bunny.domain.exchange;

public enum ExchangeType {

    FANOUT("fanout"),
    DIRECT("direct"),
    TOPIC("topic");

    private final String type;

    ExchangeType(String stringType) {
        this.type = stringType;
    }

    @Override
    public String toString() {
        return type;
    }
}
