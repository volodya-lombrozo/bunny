package org.lombrozo.bunny.connection.channel.pool;

import org.lombrozo.bunny.connection.channel.Channel;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class SingleChannelPool implements ChannelPool {

    private final Connection connection;
    private Channel channel;

    public SingleChannelPool(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void allocateChannels(ChannelFactory channelFactory) throws RabbitException {
        channel = channelFactory.newChannel();
    }

    @Override
    public Channel nextChannel() {
        return channel;
    }
}
