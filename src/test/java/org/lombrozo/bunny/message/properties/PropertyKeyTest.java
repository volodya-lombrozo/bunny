package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.AMQP;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyKeyTest {

    @Test
    public void toStringTest() {
        String expected = "content_type";
        PropertyKey key = PropertyKey.CONTENT_TYPE;

        String stringKey = key.toString();

        assertEquals(expected, stringKey);
    }


    @Test
    public void toPropertyTest() {
        String contentType = "application/json";
        AMQP.BasicProperties rawProperties = new AMQP.BasicProperties.Builder()
                .contentType(contentType)
                .build();

        Property property = PropertyKey.CONTENT_TYPE.toProperty(rawProperties);

        assertTrue(property.isNotEmpty());
        assertEquals(PropertyKey.CONTENT_TYPE, property.key());
        assertEquals(contentType, property.value());
    }
}