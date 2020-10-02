package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapResponseSource implements ResponseSource {

    private final Map<String, FutureMessage> correlationMap;

    public MapResponseSource() {
        this(new ConcurrentHashMap<>(100));
    }

    public MapResponseSource(Map<String, FutureMessage> map) {
        this.correlationMap = map;
    }

    @Override
    public void save(String correlationId, FutureMessage message) throws EmptyCorrelationId {
        checkIsEmpty(correlationId);
        correlationMap.put(correlationId, message);
    }

    void checkIsEmpty(String correlationId) throws EmptyCorrelationId {
        if (correlationId == null || correlationId.trim().isEmpty())
            throw new EmptyCorrelationId();
    }

    @Override
    public void runCallback(Message message) {
        String correlationKey = message.properties().property(PropertyKey.CORRELATION_ID);
        correlationMap.getOrDefault(correlationKey, new FutureMessage.Fake()).register(message);
        correlationMap.remove(correlationKey);
    }

}
