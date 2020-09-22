package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.domain.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Exchange extends Destination {
    String name();

    void create() throws RabbitException;

    class Fake implements Exchange{

        @Override
        public String name() {
            return "fake";
        }

        @Override
        public void create() {
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
