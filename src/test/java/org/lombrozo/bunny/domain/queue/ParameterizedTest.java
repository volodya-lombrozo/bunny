package org.lombrozo.bunny.domain.queue;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ParameterizedTest {


    @Test
    public void parameterized_emptyConstructor() {
        Parameterized parameterized = new Parameterized();

        Map<String, Object> params = parameterized.params();

        assertNotNull(params);
        assertTrue(params.isEmpty());
    }

    @Test
    public void parameterized_withParent() {
        QueueDescription.Default parent = new QueueDescription.Default();
        Parameterized parameterized = new Parameterized(parent);

        Map<String, Object> params = parameterized.params();

        assertNotNull(params);
        assertTrue(params.isEmpty());
        assertEquals(parent.autoDelete(), parameterized.autoDelete());
        assertEquals(parent.exclusive(), parameterized.exclusive());
        assertEquals(parent.durable(), parameterized.durable());
    }

    @Test
    public void parameterized_addSpecialParams() {
        String key = "key";
        String value = "value";
        Parameterized parameterized = new Parameterized().add(key, value);

        Map<String, Object> params = parameterized.params();

        assertNotNull(params);
        assertEquals(1, params.size());
        assertTrue(params.containsKey(key));
        assertTrue(params.containsValue(value));
    }
}