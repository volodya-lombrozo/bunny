package org.lombrozo.bunny.connection.subscription;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import org.lombrozo.bunny.connection.subscription.consumer.NoAckRabbitConsumer;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class NoAckConsumer implements Consumer {

    private final Work work;

    public NoAckConsumer(Work work) {
        this.work = work;
    }

    @Override
    public void doWork(Message message) throws RabbitException {
        work.doWork(message);
    }

    @Override
    public DefaultConsumer toRabbitConsumer(Channel channel) {
        return new NoAckRabbitConsumer(channel, work);
    }
}
