package org.lombrozo.bunny.connection;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.lombrozo.bunny.domain.binding.ExchangeBinding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.exchange.ExchangeDescription;
import org.lombrozo.bunny.domain.queue.QueueDescription;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.body.ByteBody;
import org.lombrozo.bunny.message.header.RabbitHeaders;
import org.lombrozo.bunny.message.properties.RabbitProperties;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.RabbitConcurrentSubscription;
import org.lombrozo.bunny.util.subscription.RabbitSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

import java.io.IOException;
import java.util.HashMap;

public class RabbitChannel implements Channel {


    private final com.rabbitmq.client.Channel channel;

    public RabbitChannel(com.rabbitmq.client.Channel channel) {
        this.channel = channel;
    }

    @Override
    public Subscription listenQueue(Queue queue, Work work) throws RabbitException {
        try {
            WorkerConsumer callback = new WorkerConsumer(channel, work);
            String consumerTag = channel.basicConsume(queue.name(), true, callback);
            return new RabbitSubscription(channel, consumerTag);
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }


    @Override
    public void publish(Destination rabbitDestination, Message message) throws RabbitException {
        try {
            AMQP.BasicProperties props = new RabbitProperties(message.properties()).toAMQPProps(message.headers(), message.deliveryMode());
            channel.basicPublish(rabbitDestination.exchangeName(), rabbitDestination.routingKey(), props, message.body().toByteArray());
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
            ExchangeDescription description = exchange.description();
            boolean autoDelete = description.autoDelete();
            boolean durable = description.durable();
            boolean internal = description.internal();
            channel.exchangeDeclare(exchange.name(), exchange.type().toString(), durable, autoDelete, internal, new HashMap<>(0));
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
                work.doWork(new RabbitMessage(new ByteBody(body), new RabbitProperties(properties), new RabbitHeaders(properties)));
            } catch (RabbitException e) {
                e.printStackTrace();
            }
        }
    }
}
