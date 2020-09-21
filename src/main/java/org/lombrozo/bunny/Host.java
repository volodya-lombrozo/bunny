package org.lombrozo.bunny;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Host {

    ConnectionFactory connectionFactory() throws RabbitException;

    Connection connect() throws RabbitException;

    Connection connect(int amountChannels) throws RabbitException;

}
