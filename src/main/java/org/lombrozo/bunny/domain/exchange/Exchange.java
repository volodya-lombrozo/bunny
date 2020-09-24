package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.domain.Declarable;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Exchange extends Destination, Declarable {
    String name();

    void create() throws RabbitException;

    ExchangeType type();

    class Fake implements Exchange{

        @Override
        public String name() {
            return "fake";
        }

        @Override
        public void create() {
        }

        @Override
        public ExchangeType type() {
            return null;
        }

        @Override
        public String exchangeName() {
            return "fake";
        }

        @Override
        public String routingKey() {
            return "fake";
        }

        @Override
        public void send(Message ignore) {
        }
    }
}
