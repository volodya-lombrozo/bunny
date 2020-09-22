package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;

import java.util.function.Consumer;

public class ConsumerHandler implements Handler {

    private final Consumer<Message> consumer;

    public ConsumerHandler(Consumer<Message> consumer) {
        this.consumer = consumer;
    }

    @Override
    public Message handle(Message message) {
        consumer.accept(message);
        return message;
    }
}
