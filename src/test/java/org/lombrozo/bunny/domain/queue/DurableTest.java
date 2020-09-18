package org.lombrozo.bunny.domain.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class DurableTest {

    @Test
    public void durable_emptyConstructor() {
        Durable durable = new Durable();

        boolean res = durable.durable();

        assertTrue(res);
    }

    @Test
    public void durable_withParent() {
        QueueDescription.Default parent = new QueueDescription.Default();

        Durable durable = new Durable(parent);

        assertTrue(durable.durable());
        assertEquals(parent.autoDelete(), durable.autoDelete());
        assertEquals(parent.exclusive(), durable.exclusive());
        assertEquals(parent.params(), durable.params());
    }

}