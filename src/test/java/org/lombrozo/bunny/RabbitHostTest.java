package org.lombrozo.bunny;

import org.junit.Test;
import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.util.address.Address;
import org.lombrozo.bunny.connection.ConnectionNameStrategy;
import org.lombrozo.bunny.util.security.Credentials;

import static org.junit.Assert.*;

public class RabbitHostTest {

    @Test
    public void constructor_hostnameOnly_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("hostname");

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostnameAndPort_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("hostname", 5672);

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostnameAndCredentials_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("hostname", new Credentials.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostnameAndUserPassword_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("hostname", "u", "p");

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostnamePortUserPassword_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("hostname", 5672, "u", "p");

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_addressAndCredentials_checkConnectionFactory() {
        RabbitHost host = new RabbitHost(new Address.FakeAddress(), new Credentials.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_addressAndCredentialsAndConnectionNaming_checkConnectionFactory() {
        RabbitHost host = new RabbitHost(new Address.FakeAddress(), new Credentials.Fake(), new ConnectionNameStrategy.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostPortAndCredentialsAndConnectionNaming_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("h", 5672, new Credentials.Fake(), new ConnectionNameStrategy.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostAndCredentialsAndConnectionNaming_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("h", new Credentials.Fake(), new ConnectionNameStrategy.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory();

        assertNotNull(connectionFactory);
    }


    @Test
    public void constructor_hostAndCredentials_checkConnectionFactory() {
        RabbitHost host = new RabbitHost("h", new Credentials.Fake());

        ConnectionFactory connectionFactory = host.connectionFactory(new ConnectionNameStrategy.Fake());

        assertNotNull(connectionFactory);
    }


}