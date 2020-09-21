package org.lombrozo.bunny.message;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class RabbitFutureMessage implements FutureMessage {

    private final List<Consumer<Message>> consumerChain;
    private volatile Message result;

    public RabbitFutureMessage() {
        this(m -> {
        });
    }

    public RabbitFutureMessage(Consumer<Message> consumer) {
        this.consumerChain = new LinkedList<>();
        consumerChain.add(consumer);
    }

    @Override
    public FutureMessage thenAccept(Consumer<Message> consumer) {
        consumerChain.add(consumer);
        return this;
    }

    @Override
    public void register(Message message) {
        consumerChain.forEach(consumer -> consumer.accept(message));
        result = message;
    }

    @Override
    public Message block() {
        while (result == null) Thread.yield();
        return result;
    }
}
