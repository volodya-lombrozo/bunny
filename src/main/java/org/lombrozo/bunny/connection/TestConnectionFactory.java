package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public class TestConnectionFactory implements ConnectionFactory {
    @Override
    public Connection connect() {
        return new TestConnection();
    }

    @Override
    public Connection connect(int amountChannels) {
        return new TestConnection();
    }

    @Override
    public Connection connect(int amountChannels, String name) throws RabbitException {
        return new TestConnection();
    }

    @Override
    public Connection connect(String name) throws RabbitException {
        return new TestConnection();
    }
}
