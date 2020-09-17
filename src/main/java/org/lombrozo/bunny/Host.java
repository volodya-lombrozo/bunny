package org.lombrozo.bunny;

import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Host {

    ConnectionFactory connectionFactory() throws  RabbitException;

}
