package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.PropertyKey;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CompositeWork implements Work {

    private final Map<String, Work> map;

    public CompositeWork() {
        this(new HashMap<>());
    }

    public CompositeWork(TypedWork... works) {
        this(Arrays.asList(works));
    }

    public CompositeWork(Collection<TypedWork> typedWork) {
        this(typedWork.stream().collect(Collectors.toMap(TypedWork::type, t -> t)));
    }

    public CompositeWork(Map<String, Work> map) {
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
