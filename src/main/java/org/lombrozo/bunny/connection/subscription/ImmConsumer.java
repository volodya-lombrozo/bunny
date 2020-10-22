package org.lombrozo.bunny.connection.subscription;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import org.lombrozo.bunny.connection.subscription.consumer.ImmediatelyAckConsumer;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class ImmConsumer implements Consumer {

    private final Work work;

    public ImmConsumer(Work work) {
        this.work = work;
    }

    @Override
    public void doWork(Message message) throws RabbitException {
        work.doWork(message);
    }

    @Override
    public DefaultConsumer toRabbitConsumer(Channel channel) {
        return new ImmediatelyAckConsumer(channel, work);
    }

}
