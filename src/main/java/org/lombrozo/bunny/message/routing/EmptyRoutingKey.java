package org.lombrozo.bunny.message.routing;

public class EmptyRoutingKey implements RoutingKey {
    @Override
    public String asString() {
        return "";
    }
}
