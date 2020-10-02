package integration;

import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.client.MapResponseSource;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.RabbitFutureMessage;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.properties.CorrelationId;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.message.properties.ReplyTo;
import org.lombrozo.bunny.util.RandomString;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;

import java.util.concurrent.*;

@Ignore("For manual testing only")
public class MapResponseSourceIntegrationTest {

    private final MapResponseSource source = new MapResponseSource();
    private final BlockingQueue<Message> messages = new ArrayBlockingQueue<>(3_000_000);
    private final int amountThreads = 25;
    private final int messagesPerThread = 100_000;
    private final int amountMessages = messagesPerThread * amountThreads;
    private final CountDownLatch latch = new CountDownLatch(amountMessages);

    @Test
    public void highload() throws InterruptedException {
        runSender();
        runReceiver();
        latch.await();
    }

    private void runSender() {
        Executors.newFixedThreadPool(1).submit(this::runSenders);
    }


    private void runSenders() {
        ExecutorService workers = Executors.newFixedThreadPool(amountThreads);
        for (int i = 0; i < amountThreads; i++) {
            workers.submit(this::sendMessages);
        }
    }

    private void sendMessages() {
        for (int i = 0; i < messagesPerThread; i++) {
            sendMessage();
        }
    }

    private void sendMessage() {
        try {
            Message message = createMessage();
            String correlationId = message.properties().property(PropertyKey.CORRELATION_ID);
            source.save(correlationId, new RabbitFutureMessage(this::handleMessage));
            messages.add(message);
        } catch (EmptyCorrelationId emptyCorrelationId) {
            emptyCorrelationId.printStackTrace();
        }
    }

    private void handleMessage(Message message) {
        latch.countDown();
        System.out.println((amountMessages - latch.getCount()) + " from " + amountMessages + " messages received");
    }

    private void runReceiver() {
        Executors.newFixedThreadPool(1).submit(this::receiveMessages);
    }


    public void receiveMessages() {
        ExecutorService receivers = Executors.newFixedThreadPool(4);
        receivers.submit(this::handleMessages);
    }

    private void handleMessages() {
        try {
            while (worldWillNotDie()) {
                source.runCallback(messages.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean worldWillNotDie() {
        return true;
    }

    private Message createMessage() {
        return new RabbitMessage(new RandomString().toString(), new ReplyTo(), new CorrelationId());
    }

}
