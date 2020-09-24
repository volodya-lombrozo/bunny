package org.lombrozo.bunny.host;

import org.lombrozo.bunny.util.address.Address;
import org.lombrozo.bunny.util.address.RealAddress;
import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.connection.RabbitConnectionFactory;
import org.lombrozo.bunny.util.security.Credentials;
import org.lombrozo.bunny.util.security.UserCredentials;
import org.lombrozo.bunny.connection.ConnectionNameStrategy;
import org.lombrozo.bunny.connection.RandomConnectionName;

public class RabbitHost implements Host {

    private final Address address;
    private final Credentials credentials;
    private final ConnectionNameStrategy nameStrategy;

    public RabbitHost(String host) {
        this(host, 5672);
    }

    public RabbitHost(String host, int port) {
        this(host, port, "guest", "guest");
    }

    public RabbitHost(String host, Credentials credentials) {
        this(host, credentials.username(), credentials.password());
    }

    public RabbitHost(String host, String username, String password) {
        this(host, 5672, username, password);
    }

    public RabbitHost(String host, int port, String username, String password) {
        this(new RealAddress(host, port), new UserCredentials(username, password));
    }

    public RabbitHost(Address address, Credentials credentials) {
        this(address, credentials, new RandomConnectionName());
    }

    public RabbitHost(String host, Credentials credentials, ConnectionNameStrategy connectionNameStrategy) {
        this(host, 5672, credentials, connectionNameStrategy);
    }

    public RabbitHost(String host, int port, Credentials credentials, ConnectionNameStrategy connectionNameStrategy) {
        this(new RealAddress(host, port), credentials, connectionNameStrategy);
    }

    public RabbitHost(Address address, Credentials credentials, ConnectionNameStrategy connectionNameStrategy) {
        this.address = address;
        this.credentials = credentials;
        this.nameStrategy = connectionNameStrategy;
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return new RabbitConnectionFactory(address, credentials, nameStrategy);
    }

    @Override
    public ConnectionFactory connectionFactory(ConnectionNameStrategy connectionNameStrategy) {
        return new RabbitConnectionFactory(address, credentials, connectionNameStrategy);
    }

}
