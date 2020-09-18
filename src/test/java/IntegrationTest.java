import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.RabbitHost;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.util.security.UserCredentials;
import org.lombrozo.bunny.util.connection.PrefixNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.message.FutureMessage;
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

        Subscription subscription = new NamedQueue("perf", connection)
                .subscribe((m) -> System.out.println("Hello world"));

        subscription.await();
    }


    @Test
    public void publishTest() throws RabbitException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();

        new NamedQueue("perf", connection)
                .send(new RabbitMessage("Hello form library!!!".getBytes()));
    }


    @Test
    public void clientTest() throws RabbitException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connect();

        FutureMessage answer = new RabbitClient(connection, "incoming",
                "reply")
                .send(new RabbitMessage("'Hello' form library".getBytes()))
                .thenAccept(Assert::assertNotNull);

        Message returnMessage = answer.block();
        assertNotNull(returnMessage);
    }
}