package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Destination {

    String exchangeName();

    String routingKey();

    void send(Message message) throws RabbitException;


    class Fake implements Destination{


        @Override
        public String exchangeName() {
            return "";
        }

        @Override
        public String routingKey() {
            return "";
        }

        @Override
        public void send(Message message) {
        }
    }
}
