package org.lombrozo.bunny.util.subscription;

import org.lombrozo.bunny.message.Message;

import java.util.function.Consumer;

public class RabbitExpectingMessage implements ExpectingMessage {

    private final Consumer<Message> messageConsumer;

    public RabbitExpectingMessage() {
        this(m -> {
        });
    }

    public RabbitExpectingMessage(Consumer<Message> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void doNext(Message message) {
        messageConsumer.accept(message);
    }

    @Override
    public void publish(Message message) {
        doNext(message);
    }
}
