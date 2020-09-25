package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.domain.Declarable;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Exchange extends Declarable {
    String name();

    void declare() throws RabbitException;

    ExchangeType type();

    void send(String routingKey, Message message) throws RabbitException;

    void send(Message message) throws RabbitException;

    class Fake implements Exchange {

        @Override
        public String name() {
            return "fake";
        }

        @Override
        public void declare() {
        }

        @Override
        public ExchangeType type() {
            return null;
        }

        @Override
        public void send(String routingKey, Message message) {

        }

        @Override
        public void send(Message ignore) {
        }

    }
}
