package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.PropertyKey;

import java.util.concurrent.ConcurrentHashMap;

public class MapResponseSource implements ResponseSource {

    private final ConcurrentHashMap<String, FutureMessage> correlationMap;

    public MapResponseSource() {
        this(new ConcurrentHashMap<>(100));
    }

    public MapResponseSource(ConcurrentHashMap<String, FutureMessage> map) {
        this.correlationMap = map;
    }

    @Override
    public void save(String correlationId, FutureMessage message) {
        correlationMap.put(correlationId, message);

    }

    @Override
    public void runCallback(Message message) {
        String correlationId = message.properties().property(PropertyKey.CORRELATION_ID);
        correlationMap.getOrDefault(correlationId, new FutureMessage.Fake()).register(message);
    }

}
