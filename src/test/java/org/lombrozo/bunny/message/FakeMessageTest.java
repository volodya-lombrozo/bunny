package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.Properties;

import static org.junit.Assert.*;

public class FakeMessageTest {


    @Test
    public void messageParamsTest() {
        Message.Fake message = new Message.Fake();

        assertNotNull(message.body());
        assertTrue(message.deliveryMode() instanceof DeliveryMode.Fake);
        assertTrue(message.headers() instanceof Headers.Fake);
        assertTrue(message.properties() instanceof Properties.Fake);
    }

}