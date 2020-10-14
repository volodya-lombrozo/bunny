package integration;

import com.rabbitmq.client.AlreadyClosedException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.PrefixNameStrategy;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.util.RandomString;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Ignore("For manual testing only")
public class ConnectionTest {

    private Queue queue;

    @Before
    public void setUp() throws Exception {
        Connection connection = new RabbitHost("localhost", 5672, "ait", "ait")
                .connectionFactory(new PrefixNameStrategy("RecoveryTest"))
                .connect();
        this.queue = new NamedQueue(connection, "testQueue");
        this.queue.declare();
    }

    @Test
    public void connectionRecoveryTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10000; i++) executorService.submit(this::sendMessage).get();
    }


    private void sendMessage() {
        try {
            queue.send(new RabbitMessage(new RandomString().toString()));
            Thread.sleep(200);
        } catch (RabbitException | InterruptedException | AlreadyClosedException e) {
            e.printStackTrace();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
