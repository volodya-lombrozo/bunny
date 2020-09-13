package org.lombrozo.bunny.connections;

import org.lombrozo.bunny.connections.channels.Channel;
import org.lombrozo.bunny.connections.channels.RabbitChannel;
import org.lombrozo.bunny.connections.channels.pool.ChannelFactory;
import org.lombrozo.bunny.connections.channels.pool.ChannelPool;
import org.lombrozo.bunny.connections.channels.pool.FixedChannelPool;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;

public class RabbitConnection implements Connection, ChannelFactory {

    private final com.rabbitmq.client.Connection connection;
    private final ChannelPool channelPool;

    public RabbitConnection(com.rabbitmq.client.Connection connection) {
        this(connection, new FixedChannelPool(4));
    }

    public RabbitConnection(com.rabbitmq.client.Connection connection, ChannelPool channelPool) {
        this.connection = connection;
        this.channelPool = channelPool;
    }

    @Override
    public void allocateChannels() throws RabbitException {
        channelPool.allocateChannels(this);
    }

    @Override
    public Channel channel() throws RabbitException {
        return channelPool.nextChannel();
    }

    @Override
    public Channel newChannel() throws RabbitException {
        try {
            return new RabbitChannel(connection.createChannel());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }
}
