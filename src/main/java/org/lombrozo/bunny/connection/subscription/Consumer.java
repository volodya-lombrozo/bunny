package org.lombrozo.bunny.connection.subscription;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Consumer {

    void doWork(Message message) throws RabbitException;

    DefaultConsumer toRabbitConsumer(Channel channel);

}
