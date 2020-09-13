package org.lombrozo.bunny.connections.channels;

import org.lombrozo.bunny.consumer.works.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.destination.RabbitDestination;
import org.lombrozo.bunny.domain.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Channel {

    void listenQueue(Queue queue, Work work) throws RabbitException;

    void publish(Destination rabbitDestination, Message message) throws RabbitException;
}
