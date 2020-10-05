package org.lombrozo.bunny.client;

import org.junit.Test;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.message.properties.CorrelationId;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class MapResponseSourceTest {

    @Test
    public void save() throws EmptyCorrelationId {
        ConcurrentHashMap<String, MessagePipeline> internalMap = new ConcurrentHashMap<>();
        ResponseSource responseSource = new MapResponseSource(internalMap);
        String correlationId = "corId";

        responseSource.save(correlationId, new MessagePipeline.Fake());

        assertEquals(1, internalMap.size());
    }

    @Test
    public void runCallback() throws EmptyCorrelationId, RabbitException {
        ResponseSource responseSource = new MapResponseSource();
        String correlationId = "corId";
        MessagePipeline message = new RabbitMessagePipeline();
        responseSource.save(correlationId, message);
        RabbitMessage expectedMessage = new RabbitMessage("Body", new CorrelationId(correlationId));

        responseSource.runCallback(expectedMessage);

        Message actualMessage = message.block();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void runCallback_removeCallbackSuccessful() throws EmptyCorrelationId, RabbitException {
        ConcurrentHashMap<String, MessagePipeline> innerMap = new ConcurrentHashMap<>();
        ResponseSource responseSource = new MapResponseSource(innerMap);
        String correlationId = "corId";
        MessagePipeline message = new RabbitMessagePipeline();
        responseSource.save(correlationId, message);
        RabbitMessage expectedMessage = new RabbitMessage("Body", new CorrelationId(correlationId));
        assertFalse(innerMap.isEmpty());

        responseSource.runCallback(expectedMessage);

        Message actualMessage = message.block();
        assertEquals(expectedMessage, actualMessage);
        assertTrue(innerMap.isEmpty());
    }

    @Test
    public void runCallback_differentMessage() throws EmptyCorrelationId {
        ConcurrentHashMap<String, MessagePipeline> innerMap = new ConcurrentHashMap<>();
        ResponseSource responseSource = new MapResponseSource(innerMap);
        String correlationId = "corId";
        MessagePipeline message = new RabbitMessagePipeline();
        responseSource.save(correlationId, message);
        RabbitMessage differentMessage = new RabbitMessage("Body", new CorrelationId());

        responseSource.runCallback(differentMessage);

        assertFalse(innerMap.isEmpty());
    }


    @Test(expected = EmptyCorrelationId.class)
    public void save_emptyCorrelationId() throws EmptyCorrelationId {
        ResponseSource responseSource = new MapResponseSource();
        String correlationId = "";
        MessagePipeline message = new RabbitMessagePipeline();

        responseSource.save(correlationId, message);

        fail();
    }


}