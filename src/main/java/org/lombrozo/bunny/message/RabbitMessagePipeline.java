package org.lombrozo.bunny.message;

import org.lombrozo.bunny.domain.Sender;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class RabbitMessagePipeline implements MessagePipeline {

    private final List<Consumer<Message>> consumerChain;
    private final Sender sender;
    private final Message sending;
    private final MessageContainer responseContainer;

    public RabbitMessagePipeline() {
        this(new Sender.Fake());
    }

    public RabbitMessagePipeline(Sender sender) {
        this(sender, new Message.Fake());
    }

    public RabbitMessagePipeline(Sender sender, Message sending) {
        this(sender, sending, new BlockedMessage());
    }

    public RabbitMessagePipeline(Sender sender, Message sending, MessageContainer responseContainer) {
        this.consumerChain = new LinkedList<>();
        this.sender = sender;
        this.sending = sending;
        this.responseContainer = responseContainer;
    }

    @Override
    public MessagePipeline send() throws RabbitException {
        sender.send(sending);
        return this;
    }

    @Override
    public MessagePipeline addResponseConsumer(Consumer<Message> consumer) {
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
