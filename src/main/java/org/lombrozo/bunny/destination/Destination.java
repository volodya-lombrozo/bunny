package org.lombrozo.bunny.destination;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Destination {

    String name();

    String routingKey();

    void send(Message message) throws RabbitException;

}
