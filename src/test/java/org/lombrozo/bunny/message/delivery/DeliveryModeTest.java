package org.lombrozo.bunny.message.delivery;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryModeTest {

    @Test
    public void fakeDeliveryModeTest() {
        String expected = "FakeDeliveryMode{}";
        DeliveryMode.Fake fake = new DeliveryMode.Fake();

        String actual = fake.toString();

        assertEquals(expected, actual);
    }

}