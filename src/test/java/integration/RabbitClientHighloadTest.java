package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.ConnectionFactory;
import org.lombrozo.bunny.domain.binding.Binding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.ExchangeDestination;
import org.lombrozo.bunny.domain.exchange.DirectExchange;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.properties.CorrelationId;
import org.lombrozo.bunny.message.properties.ReplyTo;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertArrayEquals;

//@Ignore("For manual testing only")
public class RabbitClientHighloadTest {

    RabbitClient client;
    Destination sendDestination;
    private final int amountCalls = 15000;
    private final CountDownLatch latch = new CountDownLatch(amountCalls);
    private Subscription incomingQueueSubscription;

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
        DirectExchange exchange = new DirectExchange(sendConnection, "testExchange");
        incomingQueueSubscription = sendQueue.subscribe(message -> new ExchangeDestination(exchange, "replyKey").send(message));
        exchange.declare();
        Binding firstBinding = new QueueBinding(exchange, sendQueue, "sendKey", sendConnection);
        firstBinding.declare();
        Binding secondBinding = new QueueBinding(exchange, replyQueue, "replyKey", sendConnection);
        secondBinding.declare();
        sendDestination = new ExchangeDestination(exchange, "sendKey");
    }

    @Test(timeout = 3_000)
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

    @After
    public void tearDown() throws Exception {
        incomingQueueSubscription.interrupt();
        client.cancelSubscription();
    }

    private void assertMessages(Message expected, Message actual) {
        assertArrayEquals(expected.body().toByteArray(), actual.body().toByteArray());
        latch.countDown();
    }
}
