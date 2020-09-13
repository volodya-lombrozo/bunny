package org.lombrozo.bunny.util.subscriptions;

public interface Subscription {

    void subscribe() throws InterruptedException;

    void interrupt();

}
