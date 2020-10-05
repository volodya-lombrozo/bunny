package org.lombrozo.bunny.client;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public interface Client {

    MessagePipeline sendPipeline(Destination destination, Message message) throws RabbitException;

}
