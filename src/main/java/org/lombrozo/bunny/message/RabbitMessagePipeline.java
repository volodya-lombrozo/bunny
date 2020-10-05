package org.lombrozo.bunny.message;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class RabbitMessagePipeline implements MessagePipeline {

    private final List<Consumer<Message>> consumerChain;
    private final Destination destination;
    private final Message sending;
    private final MessageContainer responseContainer;

    public RabbitMessagePipeline() {
        this(new Destination.Fake());
    }

    public RabbitMessagePipeline(Destination destination) {
        this(destination, new Message.Fake());
    }

    public RabbitMessagePipeline(Destination destination, Message sending) {
        this(destination, sending, new BlockedMessage());
    }

    public RabbitMessagePipeline(Destination destination, Message sending, MessageContainer responseContainer) {
        this.consumerChain = new LinkedList<>();
        this.destination = destination;
        this.sending = sending;
        this.responseContainer = responseContainer;
    }

    @Override
    public MessagePipeline send() throws RabbitException {
        destination.send(sending);
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
        responseContainer.put(responseMessage);
    }

    @Override
    public Message block() throws RabbitException {
        return responseContainer.receive();
    }
}
