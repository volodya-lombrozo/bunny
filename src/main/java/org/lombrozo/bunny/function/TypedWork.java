package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.PropertyKey;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.HashMap;
import java.util.Map;

public class TypedWork implements Work {

    private final Map<String, Work> map;

    public TypedWork() {
        this(new HashMap<>());
    }

    public TypedWork(Map<String, Work> map) {
        this.map = map;
    }

    public void addWorkForMessageType(String messageType, Work work) {
        map.put(messageType, work);
    }

    @Override
    public void doWork(Message message) throws RabbitException {
        String type = message.properties().property(PropertyKey.TYPE);
        if (map.containsKey(type)) map.get(type).doWork(message);
    }
}
