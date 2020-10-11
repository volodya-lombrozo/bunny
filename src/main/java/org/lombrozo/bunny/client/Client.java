package org.lombrozo.bunny.client;

import org.lombrozo.bunny.domain.Sender;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public interface Client {

    MessagePipeline pipeline(Sender destination, Message message) throws RabbitException;

}
