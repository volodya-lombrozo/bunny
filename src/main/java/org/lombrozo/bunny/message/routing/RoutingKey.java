package org.lombrozo.bunny.message.routing;

import org.lombrozo.bunny.util.RandomString;

public interface RoutingKey {

    String asString();

    class Fake implements RoutingKey {
        private final String value = new RandomString().toString();

        @Override
        public String asString() {
            return value;
        }
    }
}
