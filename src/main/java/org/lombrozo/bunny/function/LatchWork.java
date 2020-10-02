package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LatchWork implements Work {

    private final CountDownLatch latch;
    private final List<Message> messages;

    public LatchWork() {
        this(1);
    }

    public LatchWork(int amountCalls) {
        this(new CountDownLatch(amountCalls));
    }

    public LatchWork(CountDownLatch latch) {
        this.latch = latch;
        this.messages = new ArrayList<>((int) latch.getCount());
    }

    @Override
    public void doWork(Message message) {
        messages.add(message);
        latch.countDown();
    }

    public void awaitSuccess() throws RabbitException {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RabbitException(e);
        }
    }

    public Message lastMessage() {
        return messages.get(messages.size() - 1);
    }
}
