package integration;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.properties.CorrelationId;
import org.lombrozo.bunny.message.properties.ReplyTo;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertArrayEquals;

@Ignore("For manual testing only")
public class RabbitClientHighloadTest {

    RabbitClient client;
    Destination sendDestination;
    private final int amountCalls = 40000;
    private final CountDownLatch latch = new CountDownLatch(amountCalls);

    @Before
    public void setUp() throws Exception {
        ConnectionFactory factory = new RabbitHost("localhost", 5672, "ait", "ait")
                .connectionFactory();
        Connection sendConnection = factory.connect(12);
        Connection replyConnection = factory.connect(12);
        NamedQueue replyQueue = new NamedQueue(replyConnection, "replyQueue");
        replyQueue.declare();
        client = new RabbitClient(replyQueue);
        NamedQueue sendQueue = new NamedQueue(sendConnection, "sendQueue");
        sendQueue.declare();
        sendQueue.subscribe(replyQueue::send);
        sendDestination = new QueueDestination(sendQueue);
    }

    @Test
    public void highload_largeAmountOfCalls() throws RabbitException, InterruptedException {
        long start = System.nanoTime();
        for (int i = 0; i < amountCalls; i++) {
            Message expected = new RabbitMessage("message", new CorrelationId(), new ReplyTo(""));
            client.send(sendDestination, expected).thenAccept(m -> assertMessages(expected, m));
        }
        long end = System.nanoTime();
        latch.await();
        System.out.println("Test duration: " + Duration.ofNanos(end - start).getSeconds() + " sec");
    }

    private void assertMessages(Message expected, Message actual) {
        assertArrayEquals(expected.body().toByteArray(), actual.body().toByteArray());
        latch.countDown();
    }

}
