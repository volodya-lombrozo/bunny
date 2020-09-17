package org.lombrozo.bunny.consumer.work;

import org.lombrozo.bunny.message.Message;

public class RunnableWork implements Work {

    private final Runnable runnable;

    public RunnableWork(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void doWork(Message message) {
        runnable.run();
    }
}
