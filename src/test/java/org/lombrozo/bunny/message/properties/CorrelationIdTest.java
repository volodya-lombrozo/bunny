package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class CorrelationIdTest {

    @Test
    public void propertyTest_emptyConstructor() {
        CorrelationId property = new CorrelationId();

        assertEquals(PropertyKey.CORRELATION_ID, property.key());
        assertFalse(property.value().isEmpty());
    }


    @Test
    public void propertyTest_nonEmptyConstructor() {
        String expectedEmpty = "nonEmpty";

        CorrelationId property = new CorrelationId(expectedEmpty);

        assertEquals(PropertyKey.CORRELATION_ID, property.key());
        assertEquals(expectedEmpty, property.value());
    }

    @Test
    public void toStringTest() {
        String corrId = "corrId";
        String expected = "CorrelationId{" + corrId + "}";

        CorrelationId correlationId = new CorrelationId(corrId);

        String actual = correlationId.toString();
        assertEquals(expected, actual);
    }

}