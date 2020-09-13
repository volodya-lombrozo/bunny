package org.lombrozo.bunny.connections;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ConnectionFactory {

    Connection connect() throws RabbitException;
}
