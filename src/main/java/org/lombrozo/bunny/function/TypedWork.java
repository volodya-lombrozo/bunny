package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class TypedWork implements Work {

    private final String type;
    private final Work work;

    public TypedWork(String type, Work work) {
        this.type = type;
        this.work = work;
    }

    public void doIfMatch(String type, Message message) throws RabbitException {
        if (match(type))
            doWork(message);
    }

    public boolean match(String type) {
        return this.type.equals(type);
    }

    public String type() {
        return type;
    }

    @Override
    public void doWork(Message message) throws RabbitException {
        work.doWork(message);
    }
}
