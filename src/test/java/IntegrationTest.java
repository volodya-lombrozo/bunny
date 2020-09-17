import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.RabbitHost;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.consumer.TargetConsumer;
import org.lombrozo.bunny.consumer.targets.QueueTarget;
import org.lombrozo.bunny.destination.RabbitDestination;
import org.lombrozo.bunny.domain.NamedQueue;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.util.security.UserCredentials;
import org.lombrozo.bunny.util.connection.PrefixNameStrategy;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.FutureMessage;

import static org.junit.Assert.assertNotNull;

@Ignore("For manual testing only")
public class IntegrationTest {

    @Test
    public void consumeTest() throws RabbitException, InterruptedException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connectionFactory()
                .connect();

        QueueTarget target = new QueueTarget("perf", () -> System.out.println("Hello world!"));

        TargetConsumer consumer = new TargetConsumer(connection, target);

        consumer.startListening().subscribe();
    }


    @Test
    public void publishTest() throws RabbitException, InterruptedException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connectionFactory()
                .connect();

        new RabbitDestination(connection, "perf").send(new RabbitMessage("Hello form library!!!".getBytes()));
    }


    @Test//fixme: don`t work
    public void clientTest() throws RabbitException, InterruptedException {
        Connection connection = new RabbitHost("localhost",
                new UserCredentials("ait", "ait"),
                new PrefixNameStrategy("Bunny Library"))
                .connectionFactory()
                .connect();

        FutureMessage answer = new RabbitClient(connection, new NamedQueue("incoming"),
                new NamedQueue("highload"))
                .send(new RabbitMessage("Hello form library!!!".getBytes()))
                .thenAccept(Assert::assertNotNull);
    }
}