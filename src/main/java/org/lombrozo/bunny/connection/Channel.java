package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.connection.subscription.Consumer;
import org.lombrozo.bunny.domain.binding.ExchangeBinding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

public interface Channel {

    Subscription listenQueue(Queue queue, Consumer consumer) throws RabbitException;

    void publish(Destination rabbitDestination, Message message) throws RabbitException;

    void declare(Queue queue) throws RabbitException;

    void declare(Exchange exchange) throws RabbitException;

    void declare(QueueBinding binding) throws RabbitException;

    void declare(ExchangeBinding binding) throws RabbitException;

    class Fake implements Channel {

        @Override
        public Subscription listenQueue(Queue ignoreQueue, Consumer consumer) {
            return new LatchSubscription();
        }

        @Override
        public void publish(Destination ignoreDestination, Message ignoreMessage) {

        }

        @Override
        public void declare(Queue ignore) {

        }

        @Override
        public void declare(Exchange ignore) {

        }

        @Override
        public void declare(QueueBinding ignore) {

        }

        @Override
        public void declare(ExchangeBinding ignore) {

        }


    }
}
