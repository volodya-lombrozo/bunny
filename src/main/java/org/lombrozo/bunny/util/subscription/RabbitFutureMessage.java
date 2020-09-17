package org.lombrozo.bunny.util.subscription;

import org.lombrozo.bunny.message.Message;

import java.util.function.Consumer;

public class RabbitFutureMessage implements FutureMessage {

    private final FutureMessage prev;
    private final Consumer<Message> consumer;
    private Message result;

    public RabbitFutureMessage() {
        this(m -> {
        });
    }

    public RabbitFutureMessage(Consumer<Message> consumer) {
        this(new Empty(), consumer);
    }

    public RabbitFutureMessage(FutureMessage prev, Consumer<Message> consumer) {
        this.prev = prev;
        this.consumer = consumer;
    }

    @Override
    public FutureMessage thenAccept(Consumer<Message> consumer) {
        return new RabbitFutureMessage(this, consumer);
    }

    @Override
    public void register(Message message) {
        prev.register(message);
        consumer.accept(message);
        result = message;
    }

    @Override
    public Message block() {
        while (result == null) Thread.yield();
        return result;
    }
}
