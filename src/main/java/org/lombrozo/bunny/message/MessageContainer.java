package org.lombrozo.bunny.message;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface MessageContainer {

    void put(Message message);

    Message receive() throws RabbitException;

}
