package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.work.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Channel {

    void listenQueue(Queue queue, Work work) throws RabbitException;

    void publish(Destination rabbitDestination, Message message) throws RabbitException;

    void create(Queue queue) throws RabbitException;

    class Fake implements Channel {

        @Override
        public void listenQueue(Queue ignoreQueue, Work ignore) {

        }

        @Override
        public void publish(Destination ignoreDestination, Message ignoreMessage) {

        }

        @Override
        public void create(Queue queue) throws RabbitException {

        }
    }
}
