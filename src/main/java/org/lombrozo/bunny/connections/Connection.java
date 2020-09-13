package org.lombrozo.bunny.connections;

import org.lombrozo.bunny.connections.channels.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Connection {

    void allocateChannels() throws RabbitException;

    Channel channel() throws RabbitException;

}
