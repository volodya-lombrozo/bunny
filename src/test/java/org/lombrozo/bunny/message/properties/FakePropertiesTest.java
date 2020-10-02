package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class FakePropertiesTest {

    @Test
    public void addAll_ignored() {
        Properties.Fake fake = new Properties.Fake();
        CorrelationId ignored = new CorrelationId();

        Properties afterAdding = fake.addAll(ignored);

        assertFalse(afterAdding.containsProperty(ignored.key()));
    }


    @Test
    public void putProperty_ignored() {
        String expectedEmpty = "";
        Properties.Fake fake = new Properties.Fake();
        CorrelationId ignored = new CorrelationId();

        fake.put(ignored);

        assertFalse(fake.containsProperty(ignored.key()));
        assertEquals(expectedEmpty, fake.property(ignored.key()));
    }


}