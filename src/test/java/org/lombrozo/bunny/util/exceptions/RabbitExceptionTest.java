package org.lombrozo.bunny.util.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class RabbitExceptionTest {

    @Test
    public void exceptionInfoTest() {
        String expectedMessage = "Message";
        try {

            throw new RabbitException(new Exception(expectedMessage));

        } catch (RabbitException e) {
            assertNotNull(e);
            assertEquals(expectedMessage, e.getCause().getMessage());
        }
    }

}