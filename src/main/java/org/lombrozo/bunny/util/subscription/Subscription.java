package org.lombrozo.bunny.util.subscription;

public interface Subscription {

    void await() throws InterruptedException;

    void interrupt();

}
