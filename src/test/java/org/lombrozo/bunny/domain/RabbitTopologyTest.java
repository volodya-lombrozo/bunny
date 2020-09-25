package org.lombrozo.bunny.domain;

import org.junit.Test;
import org.lombrozo.bunny.domain.binding.Binding;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

public class RabbitTopologyTest {

    @Test
    public void registerTest() {
        List<Declarable> expectedList = new LinkedList<>();
        RabbitTopology topology = new RabbitTopology(expectedList);
        Exchange.Fake exchange = new Exchange.Fake();
        Binding.Fake binding = new Binding.Fake();
        Queue.Fake queue = new Queue.Fake();

        topology.register(exchange).register(binding).register(queue);

        assertEquals(3, expectedList.size());
        Iterator<Declarable> iterator = expectedList.iterator();
        assertEquals(iterator.next(), exchange);
        assertEquals(iterator.next(), binding);
        assertEquals(iterator.next(), queue);
    }


    @Test
    public void createTest() throws RabbitException {
        CountDownLatch latch = new CountDownLatch(1);
        Exchange.Fake exchange = new Exchange.Fake() {
            @Override
            public void declare() {
                latch.countDown();
            }
        };
        Topology topology = new RabbitTopology();
        topology.register(exchange);

        topology.createAll();

        assertEquals(0, latch.getCount());
    }

}