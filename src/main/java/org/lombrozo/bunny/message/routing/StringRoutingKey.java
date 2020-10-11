package org.lombrozo.bunny.message.routing;

public class StringRoutingKey implements RoutingKey{

    private final String value;

    public StringRoutingKey(String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value;
    }
}
