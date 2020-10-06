package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ChannelConsumer {

    void accept(Channel channel) throws RabbitException;
}
