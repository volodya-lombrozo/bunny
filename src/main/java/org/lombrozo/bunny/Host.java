package org.lombrozo.bunny;

import org.lombrozo.bunny.connections.ConnectionFactory;
import org.lombrozo.bunny.util.connection.ConnectionNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Host {

    ConnectionFactory connectionFactory() throws  RabbitException;

}
