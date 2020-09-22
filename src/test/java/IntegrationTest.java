import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.RabbitHost;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.consumer.QueueConsumer;
import org.lombrozo.bunny.domain.queue.Durable;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.security.UserCredentials;
import org.lombrozo.bunny.connection.PrefixNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;

import static org.junit.Assert.assertNotNull;

@Ignore("For manual testing only")
public class IntegrationTest {

    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = connect();
    }

    private Connection connect() throws RabbitException {
        return new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();
    }

    @Test
    public void consumeTest() throws RabbitException, InterruptedException {
        NamedQueue queue = new NamedQueue("perf", connection, new Durable());
        queue.create();

        Subscription subscription = queue.subscribe((m) -> System.out.println("Hello world"));

        subscription.await();
    }


    @Test
    public void publishTest() throws RabbitException {
        NamedQueue queue = new NamedQueue("perf", connection, new Durable());
        queue.create();

        new NamedQueue("perf", connection).send(new RabbitMessage("Hello", new CorrelationId()));
    }


    @Test
    public void clientTest() throws RabbitException {
        NamedQueue replyQueue = new NamedQueue("reply", connection);
        NamedQueue sendQueue = new NamedQueue("send", connection);
        replyQueue.create();
        sendQueue.create();
        RabbitClient client = new RabbitClient(sendQueue, replyQueue);
        new QueueConsumer(sendQueue, connection).subscribe(new Handler.Echo());

        FutureMessage answer = client
                .send(new RabbitMessage("'Hello' form library", new CorrelationId(), new ReplyToDestination(replyQueue)))
                .thenAccept(System.out::println)
                .thenAccept(Assert::assertNotNull);


        Message returnMessage = answer.block();
        assertNotNull(returnMessage);
    }

}