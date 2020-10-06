package org.lombrozo.bunny.message.header;

import com.rabbitmq.client.AMQP;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.*;

public class RabbitHeadersTest {

    @Test
    public void defaultConstructor() throws Exception {
        HeadersMap delegate = new HeadersMap();

        RabbitHeaders rabbitHeaders = new RabbitHeaders(delegate);

        From from = new From("from_header");
        Description description = new Description("desc");
        rabbitHeaders.add(from);
        rabbitHeaders.add(new HeadersMap(description));
        assertEquals(delegate.toMap(), rabbitHeaders.toMap());
        Header actual = rabbitHeaders.header(from.key()).orElseThrow(this::headerNotFound);
        assertEquals(from.key(), actual.key());
        assertEquals(from.value(), actual.value());
        Header actualDescription = rabbitHeaders.header(description.key()).orElseThrow(this::headerNotFound);
        assertEquals(description.key(), actualDescription.key());
        assertEquals(description.value(), actualDescription.value());
    }

    @Test
    public void amqpProperties() {
        HashMap<String, Object> headers = new HashMap<>();
        String key = "from";
        String value = "fromHeader";
        headers.put(key, value);
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().headers(headers).build();

        RabbitHeaders rabbitHeaders = new RabbitHeaders(properties);

        Optional<Header> optionalHeader = rabbitHeaders.header(key);
        assertTrue(optionalHeader.isPresent());
        Header header = optionalHeader.get();
        assertEquals(key, header.key());
        assertEquals(value, header.value());
    }

    private Exception headerNotFound() {
        return new Exception("Header not found");
    }
}