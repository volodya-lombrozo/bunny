package org.lombrozo.bunny.domain.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutoDeleteTest {


    @Test
    public void autoDelete_withParent() {
        QueueDescription.Default parent = new QueueDescription.Default();

        AutoDelete autoDelete = new AutoDelete(parent);

        assertTrue(autoDelete.autoDelete());
        assertEquals(parent.durable(), autoDelete.durable());
        assertEquals(parent.exclusive(), autoDelete.exclusive());
        assertEquals(parent.params(), autoDelete.params());
    }


    @Test
    public void autoDelete_emptyConstructor() {
        AutoDelete autoDelete = new AutoDelete();

        boolean res = autoDelete.autoDelete();

        assertTrue(res);
    }
}