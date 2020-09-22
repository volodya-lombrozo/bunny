package org.lombrozo.bunny.connection;

import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.lombrozo.bunny.util.address.Address;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.security.Credentials;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class RabbitConnectionFactoryTest {

    @Test
    public void connect_successful() throws RabbitException {
        RabbitConnectionFactory factory = new RabbitConnectionFactory(new Address.FakeAddress(), new Credentials.Fake(),
                new ConnectionNameStrategy.Fake(), new SuccessfulConnectionFactory());

        Connection connection = factory.connect();

        assertNotNull(connection);
    }


    @Test(expected = RabbitException.class)
    public void connect_exception() throws RabbitException {
        RabbitConnectionFactory factory = new RabbitConnectionFactory(new Address.FakeAddress(), new Credentials.Fake(),
                new ConnectionNameStrategy.Fake(), new ExceptionConnectionFactory());

        factory.connect();

        fail();
    }

}


class SuccessfulConnectionFactory extends ConnectionFactory {

    @Override
    public com.rabbitmq.client.Connection newConnection(String connectionName) {
        return Mockito.mock(com.rabbitmq.client.Connection.class);
    }
}


class ExceptionConnectionFactory extends ConnectionFactory {
    @Override
    public com.rabbitmq.client.Connection newConnection(String connectionName) throws IOException {
        throw new IOException("Some exception");
    }
}