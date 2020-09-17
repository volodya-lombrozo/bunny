package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ConnectionFactory {

    Connection connect() throws RabbitException;
}
