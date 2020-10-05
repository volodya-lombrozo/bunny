package org.lombrozo.bunny.message;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class RabbitMessagePipeline implements MessagePipeline {

    private final List<Consumer<Message>> consumerChain;
    private final Destination destination;
    private final BlockedMessage result;

    public RabbitMessagePipeline() {
        this(new Destination.Fake());
    }

    public RabbitMessagePipeline(Destination destination) {
        this(message -> {
        }, destination);
    }

    public RabbitMessagePipeline(Consumer<Message> consumer) {
        this(consumer, new Destination.Fake());
    }

    public RabbitMessagePipeline(Consumer<Message> consumer, Destination destination) {
        this.consumerChain = new LinkedList<>();
        consumerChain.add(consumer);
        this.destination = destination;
        this.result = new BlockedMessage();
    }


    @Override
    public MessagePipeline send(Message sendingMessage) throws RabbitException {
        destination.send(sendingMessage);
        return this;
    }

    @Override
    public MessagePipeline thenAccept(Consumer<Message> consumer) {
        consumerChain.add(consumer);
        return this;
    }

    @Override
    public void register(Message responseMessage) {
        consumerChain.forEach(consumer -> consumer.accept(responseMessage));
        result.register(responseMessage);
    }

    @Override
    public Message block() throws RabbitException {
        return result.block();
    }
}
