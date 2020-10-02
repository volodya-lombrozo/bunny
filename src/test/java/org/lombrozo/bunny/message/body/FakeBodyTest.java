package org.lombrozo.bunny.message.body;

import org.junit.Test;

import static org.junit.Assert.*;

public class FakeBodyTest {

    @Test
    public void toByteArrayTest() {
        Body.Fake fake = new Body.Fake();

        byte[] bytes = fake.toByteArray();

        assertNotNull(bytes);
    }

}