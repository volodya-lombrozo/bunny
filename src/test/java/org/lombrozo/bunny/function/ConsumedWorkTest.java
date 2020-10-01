package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.Message;

import static org.junit.Assert.*;

public class ConsumedWorkTest {


    @Test
    public void consumeWork() {
        ConsumedWork work = new ConsumedWork();
        Message.Fake message = new Message.Fake();

        work.doWork(message);

        assertTrue(work.isFinished());
        assertFalse(work.isNotFinished());
    }

    @Test
    public void checkIsNotFinishedWork() {
        ConsumedWork work = new ConsumedWork();

        assertFalse(work.isNotFinished());
    }

}