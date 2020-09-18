package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ChannelFactory {

    Channel newChannel() throws RabbitException;

}
