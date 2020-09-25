package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ConnectionFactory {

    Connection connect() throws RabbitException;

    Connection connect(int amountChannels) throws RabbitException;

    Connection connect(int amountChannels, String name) throws RabbitException;

    Connection connect(String name) throws RabbitException;


    class Fake implements ConnectionFactory {

        @Override
        public Connection connect() {
            return new Connection.Fake();
        }

        @Override
        public Connection connect(int amountChannels) throws RabbitException {
            return new Connection.Fake();
        }

        @Override
        public Connection connect(int amountChannels, String name) throws RabbitException {
            return new Connection.Fake();
        }

        @Override
        public Connection connect(String name) throws RabbitException {
            return new Connection.Fake();
        }
    }
}
