package org.lombrozo.bunny.util.subscription;

import java.util.concurrent.CountDownLatch;

public class LatchSubscription implements Subscription {

    private CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void subscribe() throws InterruptedException {
        latch.await();
    }

    @Override
    public void interrupt() {
        latch.countDown();
    }
}
