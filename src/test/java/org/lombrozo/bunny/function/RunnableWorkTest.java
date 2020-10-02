package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class RunnableWorkTest {

    @Test
    public void doWorkTest() throws RabbitException {
        CountDownLatch latch = new CountDownLatch(1);
        Work work = new RunnableWork(latch::countDown);

        work.doWork(new Message.Fake());

        assertEquals(0, latch.getCount());
    }

}