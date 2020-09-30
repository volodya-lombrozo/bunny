package org.lombrozo.bunny.connection;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.lombrozo.bunny.domain.binding.ExchangeBinding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.QueueDescription;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.body.ByteBody;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;

public class RabbitChannel implements Channel {


    private final com.rabbitmq.client.Channel channel;

    public RabbitChannel(com.rabbitmq.client.Channel channel) {
        this.channel = channel;
    }

    @Override
    public void listenQueue(Queue queue, Work work) throws RabbitException {
        try {
            channel.basicConsume(queue.name(), true, new WorkerConsumer(channel, work));
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void publish(Destination rabbitDestination, Message message) throws RabbitException {
        try {
            OutboxPropertiesAdapter adapter = new OutboxPropertiesAdapter(message.properties(), message.headers());
            channel.basicPublish(rabbitDestination.exchangeName(), rabbitDestination.routingKey(), adapter.toRabbitProperties(),
                    message.body().toByteArray());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void declare(Queue queue) throws RabbitException {
        try {
            QueueDescription description = queue.description();
            channel.queueDeclare(queue.name(), description.durable(), description.exclusive(), description.autoDelete(), description.params());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void declare(Exchange exchange) throws RabbitException {
        try {
            channel.exchangeDeclare(exchange.name(), exchange.type().toString());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void declare(QueueBinding binding) throws RabbitException {
        try {
            channel.queueBind(binding.destination(), binding.source(), binding.routingKey());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void declare(ExchangeBinding binding) throws RabbitException {
        try {
            channel.exchangeBind(binding.destination(), binding.source(), binding.routingKey());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }


    static class WorkerConsumer extends DefaultConsumer {

        private final Work work;

        WorkerConsumer(com.rabbitmq.client.Channel channel, Work work) {
            super(channel);
            this.work = work;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
            try {
                IncomingPropertiesAdapter adapter = new IncomingPropertiesAdapter(properties);
                work.doWork(new RabbitMessage(new ByteBody(body), adapter.toProperties(), adapter.toHeaders()));
            } catch (RabbitException e) {
                e.printStackTrace();
            }
        }
    }
}
