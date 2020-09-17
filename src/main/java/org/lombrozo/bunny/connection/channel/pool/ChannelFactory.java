package org.lombrozo.bunny.connection.channel.pool;

import org.lombrozo.bunny.connection.channel.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ChannelFactory {

    Channel newChannel() throws RabbitException;

}
