package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;

public class ConsumedWork implements Work {

    private volatile Message message;

    @Override
    public void doWork(Message message) {
        this.message = message;
    }

    public boolean isFinished() {
        return message != null;
    }

    public boolean isNotFinished() {
        return !isFinished();
    }
}
