package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.connection.channel.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Connection {

    void allocateChannels() throws RabbitException;

    Channel channel() throws RabbitException;

}
