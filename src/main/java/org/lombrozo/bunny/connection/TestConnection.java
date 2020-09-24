package org.lombrozo.bunny.connection;

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
}
