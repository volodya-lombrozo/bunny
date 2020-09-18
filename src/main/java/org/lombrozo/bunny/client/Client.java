package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.message.FutureMessage;


public interface Client {

    FutureMessage send(Message message) throws RabbitException;

    void publish(Message message) throws RabbitException;
}
