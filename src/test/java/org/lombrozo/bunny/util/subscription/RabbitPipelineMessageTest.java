package org.lombrozo.bunny.util.subscription;

import org.junit.Assert;
import org.junit.Test;
import org.lombrozo.bunny.message.MessagePipeline;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessagePipeline;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

import static org.junit.Assert.*;

public class RabbitPipelineMessageTest {

    @Test
    public void accept() {
        LongAdder adder = new LongAdder();
        MessagePipeline futureMessage = new RabbitMessagePipeline()
                .thenAccept((m) -> adder.increment())
                .thenAccept((m) -> adder.increment())
                .thenAccept(Assert::assertNotNull);

        futureMessage.register(new Message.Fake());


        assertEquals(2, adder.intValue());
    }


    @Test
    public void block() {
        MessagePipeline futureMessage = new RabbitMessagePipeline();
        Message expectedMessage = new Message.Fake();
        Executors.newFixedThreadPool(1)
                .submit(() -> futureMessage.register(expectedMessage));

        Message message = futureMessage.block();

        assertNotNull(message);
        assertEquals(expectedMessage, message);
    }

    @Test(timeout = 50)
    public void block_changeReference() {
        MessagePipeline futureMessage = new RabbitMessagePipeline();
        Message expectedMessage = new Message.Fake();
        futureMessage.register(expectedMessage);
        futureMessage = futureMessage.thenAccept((ignore) -> {
        });

        Message message = futureMessage.block();

        assertNotNull(message);
        assertEquals(expectedMessage, message);
    }
}