package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.domain.Declarable;
import org.lombrozo.bunny.domain.Sender;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Exchange extends Declarable, Sender {
    String name();

    ExchangeType type();

    void send(Message message, String routingKey) throws RabbitException;

    void send(Message message) throws RabbitException;

    ExchangeDescription description();

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
        public void send(Message message, String routingKey) {

        }

        @Override
        public void send(Message ignore) {
        }

        @Override
        public ExchangeDescription description() {
            return new ExchangeDescription.Fake();
        }

    }
}
