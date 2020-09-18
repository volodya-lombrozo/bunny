package org.lombrozo.bunny.domain.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExclusiveTest {


    @Test
    public void exclusive_emptyConstructor() {
        Exclusive exclusive = new Exclusive();

        boolean res = exclusive.exclusive();

        assertTrue(res);
    }

    @Test
    public void exclusive_withParent() {
        QueueDescription.Default parent = new QueueDescription.Default();

        Exclusive exclusive = new Exclusive(parent);

        assertTrue(exclusive.exclusive());
        assertEquals(parent.durable(), exclusive.durable());
        assertEquals(parent.autoDelete(), exclusive.autoDelete());
        assertEquals(parent.params(), exclusive.params());
    }
}