package org.lombrozo.bunny.host;

import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.connection.ConnectionNameStrategy;
import org.lombrozo.bunny.connection.TestConnectionFactory;

public class TestHost implements Host {
    @Override
    public ConnectionFactory connectionFactory() {
        return new TestConnectionFactory();
    }

    @Override
    public ConnectionFactory connectionFactory(ConnectionNameStrategy connectionNameStrategy) {
        return new TestConnectionFactory();
    }
}
