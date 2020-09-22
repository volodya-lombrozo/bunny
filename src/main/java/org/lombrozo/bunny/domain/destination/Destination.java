package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Destination {

    String exchangeName();

    String routingKey();

    void send(Message message) throws RabbitException;

}
