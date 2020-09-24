package org.lombrozo.bunny.connection;

public class TestConnectionFactory implements ConnectionFactory {
    @Override
    public Connection connect() {
        return new TestConnection();
    }

    @Override
    public Connection connect(int amountChannels) {
        return new TestConnection();
    }
}
