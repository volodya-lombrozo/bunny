package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.header.HeadersMap;
import org.lombrozo.bunny.message.header.StringHeader;
import org.lombrozo.bunny.message.header.To;

import java.util.Map;

import static org.junit.Assert.*;

public class HeadersMapTest {

    @Test
    public void addTest() {
        Headers headers = new HeadersMap();
        String key = "key";
        String value = "value";

        headers.add(new StringHeader(key, value)).add(new To("to"));

        Map<String, Object> map = headers.toMap();
        assertNotNull(map);
        assertTrue(map.containsKey(key));
        assertTrue(map.containsValue(value));
        assertEquals(2, map.size());
    }


}