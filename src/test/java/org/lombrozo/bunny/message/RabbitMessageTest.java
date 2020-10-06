package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.*;

import static org.junit.Assert.*;

public class RabbitMessageTest {

    @Test
    public void bodyPropertiesConstructor() {
        Body.Fake body = new Body.Fake();
        Properties.Fake properties = new Properties.Fake();

        RabbitMessage message = new RabbitMessage(body, properties);

        assertEquals(body, message.body());
        assertEquals(properties, message.properties());
    }


    @Test
    public void bodyPropertyArrayConstructor() {
        Body.Fake body = new Body.Fake();

        RabbitMessage message = new RabbitMessage(body, new CorrelationId(), new Type("type"));

        assertEquals(body, message.body());
        assertTrue(message.properties().containsProperty(PropertyKey.CORRELATION_ID));
        assertTrue(message.properties().containsProperty(PropertyKey.TYPE));
    }


    @Test
    public void stringBodyPropertiesConstructor() {
        Properties.Fake properties = new Properties.Fake();

        RabbitMessage message = new RabbitMessage("body", properties);

        assertArrayEquals("body".getBytes(), message.body().toByteArray());
        assertEquals(properties, message.properties());
    }


    @Test
    public void bodyAndHeadersConstructor() {
        Body.Fake body = new Body.Fake();
        Headers headers = new Headers.Fake();

        RabbitMessage message = new RabbitMessage(body, headers);

        assertEquals(body, message.body());
        assertEquals(headers, message.headers());
    }

    @Test
    public void messageAndAdditionalPropertiesConstructor() {
        RabbitMessage first = new RabbitMessage("message", new PropertiesSet());
        ReplyTo replyTo = new ReplyTo("replyTo");

        RabbitMessage message = new RabbitMessage(first, replyTo);

        assertTrue(message.properties().containsProperty(PropertyKey.REPLY_TO));
        assertEquals(first.body(), message.body());
        assertEquals(first.properties(), message.properties());
        assertEquals(first.headers(), message.headers());
        assertEquals(first.deliveryMode(), message.deliveryMode());
    }

}