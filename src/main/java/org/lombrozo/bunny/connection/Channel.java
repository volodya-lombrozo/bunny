package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.domain.binding.Binding;
import org.lombrozo.bunny.domain.binding.ExchangeBinding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Channel {

    void listenQueue(Queue queue, Work work) throws RabbitException;

    void publish(Destination rabbitDestination, Message message) throws RabbitException;

    void create(Queue queue) throws RabbitException;

    void create(Exchange exchange) throws RabbitException;

    void create(QueueBinding binding) throws RabbitException;

    void create(ExchangeBinding binding) throws RabbitException;

    class Fake implements Channel {

        @Override
        public void listenQueue(Queue ignoreQueue, Work ignore) {

        }

        @Override
        public void publish(Destination ignoreDestination, Message ignoreMessage) {

        }

        @Override
        public void create(Queue ignore) {

        }

        @Override
        public void create(Exchange ignore) {

        }

        @Override
        public void create(QueueBinding ignore) {

        }

        @Override
        public void create(ExchangeBinding ignore) {

        }


    }
}
