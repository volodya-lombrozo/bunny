package org.lombrozo.bunny.host;

import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.connection.ConnectionNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Host {

    ConnectionFactory connectionFactory() throws RabbitException;

    ConnectionFactory connectionFactory(ConnectionNameStrategy connectionNameStrategy) throws RabbitException;

}
