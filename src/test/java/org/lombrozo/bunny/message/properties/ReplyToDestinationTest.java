package org.lombrozo.bunny.message.properties;

import org.junit.Test;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.queue.Queue;

import static org.junit.Assert.*;

public class ReplyToDestinationTest {


    @Test
    public void propertyTest_queueConstructor() {
        Queue.Fake queue = new Queue.Fake();
        String prefix = new QueueDestination(queue).exchangeName();
        String postfix = new QueueDestination(queue).routingKey();
        String expected = prefix + "." + postfix;

        Property property = new ReplyToDestination(queue);

        assertTrue(property.isNotEmpty());
        assertEquals(PropertyKey.REPLY_TO, property.key());
        assertEquals(expected, property.value());
    }

}