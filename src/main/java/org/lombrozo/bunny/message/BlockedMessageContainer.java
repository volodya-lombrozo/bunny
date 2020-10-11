package org.lombrozo.bunny.message;

import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockedMessageContainer implements MessageContainer {

    private final BlockingQueue<Message> message;

    public BlockedMessageContainer() {
        this(new ArrayBlockingQueue<>(1));
    }

    public BlockedMessageContainer(BlockingQueue<Message> message) {
        this.message = message;
    }

    @Override
    public void put(Message message) {
        this.message.add(message);
    }

    @Override
    public Message receive() throws RabbitException {
        try {
            return message.take();
        } catch (InterruptedException e) {
            throw new RabbitException(e);
        }
    }
}
