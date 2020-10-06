package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.Properties;
import org.lombrozo.bunny.message.properties.PropertiesSet;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.message.properties.Type;

import static org.junit.Assert.*;

public class RPCMessageTest {

    @Test
    public void bodyAndPropertiesConstructor() {
        Body.Fake body = new Body.Fake();
        Type type = new Type("Type");

        RPCMessage message = new RPCMessage(body, type);

        assertEquals(body, message.body());
        assertTrue(message.properties().containsProperty(PropertyKey.TYPE));
        assertTrue(message.properties().containsProperty(PropertyKey.CORRELATION_ID));
    }


    @Test
    public void fullConstructor() {
        Body.Fake body = new Body.Fake();
        DeliveryMode.Fake deliveryMode = new DeliveryMode.Fake();
        Headers.Fake headers = new Headers.Fake();
        Properties properties = new PropertiesSet();

        RPCMessage message = new RPCMessage(body, properties, headers, deliveryMode);

        assertEquals(body, message.body());
        assertEquals(deliveryMode, message.deliveryMode());
        assertEquals(headers, message.headers());
        assertEquals(properties, message.properties());
        assertTrue(message.properties().containsProperty(PropertyKey.CORRELATION_ID));
    }

}