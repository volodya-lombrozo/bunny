package org.lombrozo.bunny.connection.subscription.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.body.ByteBody;
import org.lombrozo.bunny.message.header.RabbitHeaders;
import org.lombrozo.bunny.message.properties.RabbitProperties;

public class NoAckRabbitConsumer extends DefaultConsumer {

    private final Work work;

    public NoAckRabbitConsumer(com.rabbitmq.client.Channel channel, Work work) {
        super(channel);
        this.work = work;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        try {
            work.doWork(new RabbitMessage(new ByteBody(body), new RabbitProperties(properties), new RabbitHeaders(properties)));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
