package org.lombrozo.bunny.connection.pool;

import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.ChannelFactory;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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

    private final AtomicInteger integer = new AtomicInteger(0);

    @Override
    public Channel nextChannel() {
        return channels[integer.incrementAndGet() % amountChannels];
    }

    @Override
    public List<Channel> toList() {
        return Arrays.asList(channels);
    }
}
