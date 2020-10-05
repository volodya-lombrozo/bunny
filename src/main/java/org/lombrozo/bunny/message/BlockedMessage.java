package org.lombrozo.bunny.message;

import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockedMessage implements MessageContainer{

    private final BlockingQueue<Message> message;

    public BlockedMessage() {
        this(new ArrayBlockingQueue<>(1));
    }

    public BlockedMessage(BlockingQueue<Message> message) {
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
