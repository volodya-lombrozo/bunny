package integration;

import com.rabbitmq.tools.json.JSONUtil;
import com.rabbitmq.tools.jsonrpc.JacksonJsonRpcMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.client.RabbitClient;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.consumer.ResponsibleQueueConsumer;
import org.lombrozo.bunny.domain.queue.Durable;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.message.properties.CorrelationId;
import org.lombrozo.bunny.message.properties.ReplyTo;
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
                .connectionFactory()
                .connect();
    }

    @Test
    public void consumeTest() throws RabbitException, InterruptedException {
        NamedQueue queue = new NamedQueue(connection, "perf", new Durable());
        queue.declare();

        Subscription subscription = queue.subscribe((m) -> System.out.println("Hello world"));

        subscription.await();
    }


    @Test
    public void publishTest() throws RabbitException {
        NamedQueue queue = new NamedQueue(connection, "perf", new Durable());
        queue.declare();

        new NamedQueue(connection, "perf").send(new RabbitMessage("Hello", new CorrelationId()));
    }


    @Test
    public void clientTest() throws RabbitException {
        NamedQueue replyQueue = new NamedQueue(connection, "reply");
        NamedQueue sendQueue = new NamedQueue(connection, "send");
        replyQueue.declare();
        sendQueue.declare();
        RabbitClient client = new RabbitClient(replyQueue);
        new ResponsibleQueueConsumer(sendQueue, connection).subscribe(new Handler.Echo());

        MessagePipeline answer = client
                .pipeline(sendQueue, new RabbitMessage("'Hello' form library", new CorrelationId(), new ReplyTo(replyQueue)))
                .addResponseConsumer(System.out::println)
                .addResponseConsumer(Assert::assertNotNull)
                .send();


        Message returnMessage = answer.block();
        assertNotNull(returnMessage);
    }

}