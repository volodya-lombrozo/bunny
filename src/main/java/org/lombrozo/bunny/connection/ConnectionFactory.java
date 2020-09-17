package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ConnectionFactory {

    Connection connect() throws RabbitException;


    class Fake implements ConnectionFactory {

        @Override
        public Connection connect() {
            return new Connection.Fake();
        }
    }
}
