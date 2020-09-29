package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public interface Client {

    FutureMessage send(Message message) throws RabbitException;

    void publish(Message message) throws RabbitException;
}
