package org.lombrozo.bunny.client;

import org.junit.Test;
import org.lombrozo.bunny.message.*;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class MapResponseSourceTest {

    @Test
    public void save() {
        ConcurrentHashMap<String, FutureMessage> internalMap = new ConcurrentHashMap<>();
        ResponseSource responseSource = new MapResponseSource(internalMap);
        String correlationId = "corId";

        responseSource.save(correlationId, new FutureMessage.Fake());

        assertEquals(1, internalMap.size());
    }

    @Test
    public void runCallback() {
        ResponseSource responseSource = new MapResponseSource();
        String correlationId = "corId";
        FutureMessage message = new RabbitFutureMessage();
        responseSource.save(correlationId, message);
        RabbitMessage expectedMessage = new RabbitMessage("Body", new CorrelationId(correlationId));

        responseSource.runCallback(expectedMessage);

        Message actualMessage = message.block();
        assertEquals(expectedMessage, actualMessage);
    }
}