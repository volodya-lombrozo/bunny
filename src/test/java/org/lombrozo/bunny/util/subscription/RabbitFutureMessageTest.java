package org.lombrozo.bunny.util.subscription;

import org.junit.Assert;
import org.junit.Test;
import org.lombrozo.bunny.message.Message;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

import static org.junit.Assert.*;

public class RabbitFutureMessageTest {

    @Test
    public void accept() {
        LongAdder adder = new LongAdder();
        FutureMessage futureMessage = new RabbitFutureMessage()
                .thenAccept((m) -> adder.increment())
                .thenAccept((m) -> adder.increment())
                .thenAccept(Assert::assertNotNull);

        futureMessage.register(new Message.Fake());


        assertEquals(2, adder.intValue());
    }


    @Test
    public void block() {
        FutureMessage futureMessage = new RabbitFutureMessage();
        Message expectedMessage = new Message.Fake();
        Executors.newFixedThreadPool(1)
                .submit(() -> futureMessage.register(expectedMessage));

        Message message = futureMessage.block();

        assertNotNull(message);
        assertEquals(expectedMessage, message);
    }
}