package org.lombrozo.bunny.domain.binding;

import org.junit.Test;

import static org.junit.Assert.*;

public class BindingTest {

    @Test
    public void fakeBindingTest() {
        String expectedEmpty = "";

        Binding.Fake fake = new Binding.Fake();

        fake.declare();
        assertEquals(expectedEmpty, fake.destination());
        assertEquals(expectedEmpty, fake.routingKey());
        assertEquals(expectedEmpty, fake.source());

    }

}