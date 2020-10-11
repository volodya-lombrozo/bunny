package org.lombrozo.bunny.domain.destination;

public interface Destination {

    String exchangeName();

    String routingKey();

    class Fake implements Destination{

        @Override
        public String exchangeName() {
            return "";
        }

        @Override
        public String routingKey() {
            return "";
        }

    }
}
