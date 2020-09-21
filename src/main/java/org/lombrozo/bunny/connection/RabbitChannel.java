package org.lombrozo.bunny.connection;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.lombrozo.bunny.domain.queue.QueueDescription;
import org.lombrozo.bunny.message.ByteBody;
import org.lombrozo.bunny.work.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            channel.basicPublish(rabbitDestination.exchangeName(), rabbitDestination.routingKey(), new AMQP.BasicProperties.Builder()
                            .contentType("text/plain")
                            .build(),
                    message.body().toByteArray());
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }

    @Override
    public void create(Queue queue) throws RabbitException {
        try {
            QueueDescription description = queue.description();
            channel.queueDeclare(queue.name(), description.durable(), description.exclusive(), description.autoDelete(), description.params());
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
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            work.doWork(new RabbitMessage(new ByteBody(body)));
        }
    }
}
