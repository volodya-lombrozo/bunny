package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Connection {

    void allocateChannels() throws RabbitException;

    Channel channel() throws RabbitException;

    class Fake implements Connection {

        @Override
        public void allocateChannels() {
        }

        @Override
        public Channel channel() {
            return new Channel.Fake();
        }
    }

}
