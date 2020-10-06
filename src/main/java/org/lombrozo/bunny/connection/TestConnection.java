package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public class TestConnection implements Connection {

    private Channel channel;

    public TestConnection() {
        this(new TestChannel());
    }

    public TestConnection(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void allocateChannels() {
        channel = new TestChannel();
    }

    @Override
    public Channel channel() {
        return channel;
    }

    @Override
    public void forAllChannels(ChannelConsumer consumer) throws RabbitException {
        consumer.accept(channel);
    }
}
