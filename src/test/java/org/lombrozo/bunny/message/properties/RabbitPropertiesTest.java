package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.AMQP;
import org.junit.Test;

import static org.junit.Assert.*;

public class RabbitPropertiesTest {

    @Test
    public void property() {
        AMQP.BasicProperties raw = new AMQP.BasicProperties.Builder()
                .priority(102)
                .build();
        RabbitProperties properties = new RabbitProperties(raw);
        PropertyKey key = PropertyKey.PRIORITY;

        String property = properties.property(key);

        assertEquals("102", property);
        assertTrue(properties.containsProperty(key));
    }


    @Test
    public void putProperty() {
        PropertiesSet delegate = new PropertiesSet();
        Properties properties = new RabbitProperties(delegate);

        properties.put(new ReplyTo());

        PropertyKey key = PropertyKey.REPLY_TO;
        assertTrue(properties.containsProperty(key));
        assertTrue(delegate.containsProperty(key));
    }


    @Test
    public void addAll() {
        PropertiesSet delegate = new PropertiesSet();
        Properties properties = new RabbitProperties(delegate);

        properties.addAll(new ReplyTo(), new CorrelationId());

        PropertyKey replyTo = PropertyKey.REPLY_TO;
        PropertyKey corrId = PropertyKey.REPLY_TO;
        assertTrue(properties.containsProperty(replyTo));
        assertTrue(delegate.containsProperty(replyTo));
        assertTrue(properties.containsProperty(corrId));
        assertTrue(delegate.containsProperty(corrId));
    }
}