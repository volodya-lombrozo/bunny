package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.header.Header;
import org.lombrozo.bunny.message.header.SpringTypeId;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.*;
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

    public void addWorkForMessageType(Class<?> type, Work work) {
        TypedWork typedWork = new TypedWork(type, work);
        this.addWorkForMessageType(typedWork.type(), typedWork);
    }

    @Override
    public void doWork(Message message) throws RabbitException {
        try {
            String type = extractType(message).orElseThrow(() -> new EmptyCorrelationId(message));
            if (map.containsKey(type)) map.get(type).doWork(message);
        } catch (EmptyCorrelationId e) {
            throw new RabbitException(e);
        }
    }

    private Optional<String> extractType(Message message) {
        String typeFromProperty = message.properties().property(PropertyKey.TYPE);
        if (isNotEmpty(typeFromProperty)) {
            return Optional.of(typeFromProperty);
        } else return message.headers()
                .header(new SpringTypeId().key())
                .map(Header::value)
                .filter(this::isNotEmpty);
    }

    private boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }
}
