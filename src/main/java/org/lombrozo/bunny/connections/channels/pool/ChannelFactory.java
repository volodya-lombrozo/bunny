package org.lombrozo.bunny.connections.channels.pool;

import org.lombrozo.bunny.connections.channels.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ChannelFactory {

    Channel newChannel() throws RabbitException;

}
