package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;


public interface Connection {

    void allocateChannels() throws RabbitException;

    Channel channel() throws RabbitException;

    void forAllChannels(ChannelConsumer consumer) throws RabbitException;

    class Fake implements Connection {

        @Override
        public void allocateChannels() {
        }

        @Override
        public Channel channel() {
            return new Channel.Fake();
        }

        @Override
        public void forAllChannels(ChannelConsumer consumer) {

        }
    }

}
