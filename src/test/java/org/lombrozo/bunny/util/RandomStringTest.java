package org.lombrozo.bunny.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStringTest {

    @Test
    public void testToString() {
        RandomString random = new RandomString();

        String randomString = random.toString();

        assertNotNull(randomString);
    }
}