package org.lombrozo.bunny.connection.channel.pool;

import org.lombrozo.bunny.connection.channel.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ChannelPool {

    void allocateChannels(ChannelFactory channelFactory) throws RabbitException;

    Channel nextChannel();

}
