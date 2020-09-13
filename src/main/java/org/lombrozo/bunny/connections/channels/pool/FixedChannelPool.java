package org.lombrozo.bunny.connections.channels.pool;

import org.lombrozo.bunny.connections.channels.Channel;
import org.lombrozo.bunny.connections.Connection;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Random;

public class FixedChannelPool implements ChannelPool {

    private final int amountChannels;
    private final Channel[] channels;

    public FixedChannelPool(int amountChannels) {
        this.amountChannels = amountChannels;
        this.channels = new Channel[amountChannels];
    }

    @Override
    public void allocateChannels(ChannelFactory channelFactory) throws RabbitException {
        for (int i = 0; i < amountChannels; i++) channels[i] = channelFactory.newChannel();
    }

    @Override
    public Channel nextChannel() {
        return channels[new Random().nextInt(amountChannels - 1)];
    }
}
