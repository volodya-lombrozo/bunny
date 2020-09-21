import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.RabbitHost;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.queue.Durable;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.security.UserCredentials;
import org.lombrozo.bunny.util.connection.PrefixNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;

import static org.junit.Assert.assertNotNull;

@Ignore("For manual testing only")
public class IntegrationTest {

    @Test
    public void consumeTest() throws RabbitException, InterruptedException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();

        NamedQueue queue = new NamedQueue("perf", connection);
        queue.create();
        Subscription subscription = queue
                .subscribe((m) -> System.out.println("Hello world"));

        subscription.await();
    }


    @Test
    public void publishTest() throws RabbitException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();
        NamedQueue queue = new NamedQueue("perf", connection, new Durable());
        queue.create();

        new NamedQueue("perf", connection)
                .send(new RabbitMessage("Hello", new CorrelationId()));
    }


    @Test
    public void clientTest() throws RabbitException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();

        NamedQueue replyQueue = new NamedQueue("reply", connection);
        NamedQueue sendQueue = new NamedQueue("send", connection);

        replyQueue.create();
        sendQueue.create();


        FutureMessage answer = new RabbitClient(sendQueue, replyQueue)
                .send(new RabbitMessage("'Hello' form library", new CorrelationId()))
                .thenAccept(System.out::println)
                .thenAccept(Assert::assertNotNull);

        sendQueue.subscribe(message -> {
            try {
                message.properties().put(new ReplyTo("BBd"));
                replyQueue.send(message);
            } catch (RabbitException e) {
                e.printStackTrace();
            }
        });

        Message returnMessage = answer.block();
        assertNotNull(returnMessage);
    }
}