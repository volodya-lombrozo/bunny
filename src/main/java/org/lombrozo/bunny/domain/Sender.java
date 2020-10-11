package org.lombrozo.bunny.domain;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Sender {

    void send(Message message) throws RabbitException;

    class Fake implements Sender {
        @Override
        public void send(Message ignored) {
        }
    }

}
