package org.lombrozo.bunny.connection.pool;

import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.ChannelFactory;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.List;

public interface ChannelPool {

    void allocateChannels(ChannelFactory channelFactory) throws RabbitException;

    Channel nextChannel();

    List<Channel> toList();

}
