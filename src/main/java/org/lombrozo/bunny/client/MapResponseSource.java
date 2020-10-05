package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.MessagePipeline;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapResponseSource implements ResponseSource {

    private final Map<String, MessagePipeline> correlationMap;

    public MapResponseSource() {
        this(new ConcurrentHashMap<>(100));
    }

    public MapResponseSource(Map<String, MessagePipeline> map) {
        this.correlationMap = map;
    }

    @Override
    public void save(String correlationId, MessagePipeline message) throws EmptyCorrelationId {
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
        correlationMap.getOrDefault(correlationKey, new MessagePipeline.Fake()).register(message);
        correlationMap.remove(correlationKey);
    }

}
