package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppIdTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property appId = new AppId(expected);

        assertEquals(expected, appId.value());
        assertEquals(PropertyKey.APP_ID, appId.key());
    }

}