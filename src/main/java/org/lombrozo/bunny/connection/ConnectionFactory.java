package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ConnectionFactory {

    Connection connect() throws RabbitException;

    Connection connect(int amountChannels) throws RabbitException;


    class Fake implements ConnectionFactory {

        @Override
        public Connection connect() {
            return new Connection.Fake();
        }

        @Override
        public Connection connect(int amountChannels) throws RabbitException {
            return new Connection.Fake();
        }
    }
}
