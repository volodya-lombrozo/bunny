package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.connection.pool.FixedChannelPool;
import org.lombrozo.bunny.util.address.Address;
import org.lombrozo.bunny.util.security.Credentials;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConnectionFactory implements ConnectionFactory {

    private final com.rabbitmq.client.ConnectionFactory connectionFactory;
    private final ConnectionNameStrategy connectionNameStrategy;

    public RabbitConnectionFactory(Address address, Credentials credentials) {
        this(address, credentials, new RandomConnectionName());
    }

    public RabbitConnectionFactory(Address address, Credentials credentials, ConnectionNameStrategy connectionNameStrategy) {
        this(address, credentials, connectionNameStrategy, new com.rabbitmq.client.ConnectionFactory());
    }


    public RabbitConnectionFactory(Address address, Credentials credentials, ConnectionNameStrategy connectionNameStrategy, com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory) {
        this.connectionNameStrategy = connectionNameStrategy;
        connectionFactory = rabbitConnectionFactory;
        connectionFactory.setUsername(credentials.username());
        connectionFactory.setPassword(credentials.password());
        connectionFactory.setAutomaticRecoveryEnabled(false);
        connectionFactory.setVirtualHost(address.virtualHost());
        connectionFactory.setHost(address.host());
        connectionFactory.setPort(address.port());
    }


    @Override
    public Connection connect() throws RabbitException {
        return connect(4);
    }

    @Override
    public Connection connect(int amountChannels) throws RabbitException {
        try {
            RabbitConnection connection = new RabbitConnection(connectionFactory.newConnection(connectionNameStrategy.connectionName()), new FixedChannelPool(amountChannels));
            connection.allocateChannels();
            return connection;
        } catch (IOException | TimeoutException e) {
            throw new RabbitException(e);
        }
    }
}
