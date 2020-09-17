package org.lombrozo.bunny.consumer.targets;

import org.lombrozo.bunny.connection.channel.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Target {

    void listen(Channel channel) throws RabbitException;

}
