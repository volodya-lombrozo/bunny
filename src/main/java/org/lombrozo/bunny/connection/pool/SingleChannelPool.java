package org.lombrozo.bunny.connection.pool;

import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.ChannelFactory;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class SingleChannelPool implements ChannelPool {

    private final Connection connection;
    private Channel channel;

    public SingleChannelPool(Connection connection) {
        this.connection = connection;
    }

    public SingleChannelPool(Channel channel) {
        this.channel = channel;
        this.connection = new Connection.Fake();
    }

    @Override
    public void allocateChannels(ChannelFactory channelFactory) throws RabbitException {
        if (channel != null)
            channel = channelFactory.newChannel();
    }

    @Override
    public Channel nextChannel() {
        return channel;
    }
}
