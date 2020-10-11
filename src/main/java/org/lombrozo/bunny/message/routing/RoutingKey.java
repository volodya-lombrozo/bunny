package org.lombrozo.bunny.message.routing;

import org.lombrozo.bunny.util.RandomString;

public interface RoutingKey {
    String asString();

    class Fake implements RoutingKey {
        @Override
        public String asString() {
            return new RandomString().toString();
        }
    }
}
