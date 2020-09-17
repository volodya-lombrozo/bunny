package org.lombrozo.bunny.util.subscription;

public interface Subscription {

    void subscribe() throws InterruptedException;

    void interrupt();

}
