package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LatchWorkTest {

    @Test
    public void doWorkTest() {
        CountDownLatch latch = new CountDownLatch(1);
        LatchWork work = new LatchWork(latch);
        Message.Fake message = new Message.Fake();

        work.doWork(message);

        assertEquals(message, work.lastMessage());
        assertEquals(0, latch.getCount());
    }


    @Test(expected = RabbitException.class)
    public void interruptTest() throws RabbitException {
        CountDownLatch latch = new AbortCountDownLatch(1);
        LatchWork work = new LatchWork(latch);

        work.awaitSuccess();

        fail();
    }

}

class AbortCountDownLatch extends CountDownLatch {

    public AbortCountDownLatch(int count) {
        super(count);
    }

    @Override
    public void await() throws InterruptedException {
        throw new InterruptedException("Some message");
    }
}
