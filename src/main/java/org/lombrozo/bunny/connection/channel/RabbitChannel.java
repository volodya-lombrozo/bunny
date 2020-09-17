package org.lombrozo.bunny.connection.channel;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.lombrozo.bunny.consumer.work.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.domain.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessage;
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
            channel.basicPublish(rabbitDestination.name(), rabbitDestination.routingKey(), new AMQP.BasicProperties.Builder()
                    .contentType("text/plain")
                    .build(),
                    message.body());
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
            work.doWork(new RabbitMessage(body));
        }
    }
}
