package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;

public class IgnoreWork implements Work {
    @Override
    public void doWork(Message ignore) {

    }
}
