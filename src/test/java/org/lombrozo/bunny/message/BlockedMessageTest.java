package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

public class BlockedMessageTest {


    @Test
    public void registerTest() throws RabbitException {
        BlockedMessage blockedMessage = new BlockedMessage();
        Message.Fake sending = new Message.Fake();

        blockedMessage.register(sending);

        Message actual = blockedMessage.block();
        assertEquals(sending, actual);
    }

    @Test(expected = RabbitException.class)
    public void block_interruptedBlocking() throws RabbitException {
        BlockedMessage message = new BlockedMessage(new InterruptedQueue());

        message.block();

        fail();
    }

}

class InterruptedQueue extends ArrayBlockingQueue<Message> {

    public InterruptedQueue() {
        super(1);
    }

    @Override
    public Message take() throws InterruptedException {
        throw new InterruptedException("Something went wrong");
    }
}