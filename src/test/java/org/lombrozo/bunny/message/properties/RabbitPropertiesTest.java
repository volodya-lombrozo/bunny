package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.junit.Test;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.delivery.Persistent;
import org.lombrozo.bunny.message.header.Headers;

import java.util.Date;

import static org.junit.Assert.*;

public class RabbitPropertiesTest {

    @Test
    public void property() {
        AMQP.BasicProperties raw = new AMQP.BasicProperties.Builder()
                .priority(102)
                .build();
        RabbitProperties properties = new RabbitProperties(raw);
        PropertyKey key = PropertyKey.PRIORITY;

        String property = properties.property(key);

        assertEquals("102", property);
        assertTrue(properties.containsProperty(key));
    }


    @Test
    public void putProperty() {
        PropertiesSet delegate = new PropertiesSet();
        Properties properties = new RabbitProperties(delegate);

        properties.put(new ReplyTo());

        PropertyKey key = PropertyKey.REPLY_TO;
        assertTrue(properties.containsProperty(key));
        assertTrue(delegate.containsProperty(key));
    }


    @Test
    public void addAll() {
        PropertiesSet delegate = new PropertiesSet();
        Properties properties = new RabbitProperties(delegate);

        properties.addAll(new ReplyTo(), new CorrelationId());

        PropertyKey replyTo = PropertyKey.REPLY_TO;
        PropertyKey corrId = PropertyKey.REPLY_TO;
        assertTrue(properties.containsProperty(replyTo));
        assertTrue(delegate.containsProperty(replyTo));
        assertTrue(properties.containsProperty(corrId));
        assertTrue(delegate.containsProperty(corrId));
    }


    @Test
    public void toAMQPProps() {
        String corrId = "corrId";
        String replyTo = "replyTo";
        int priority = 1;
        PropertiesSet delegate = new PropertiesSet(new CorrelationId(corrId), new ReplyTo(replyTo), new Priority(priority));
        Headers headers = new Headers.Fake();
        DeliveryMode deliveryMode = new Persistent();

        RabbitProperties rabbitProperties = new RabbitProperties(delegate);

        AMQBasicProperties amqpProps = rabbitProperties.toAMQPProps(headers, deliveryMode);
        assertEquals(corrId, amqpProps.getCorrelationId());
        assertEquals(replyTo, amqpProps.getReplyTo());
        assertEquals(Integer.valueOf(priority), amqpProps.getPriority());
    }


    @Test
    public void toAMQPProps_timestamp() {
        Date date = new Date();
        PropertiesSet delegate = new PropertiesSet(new Timestamp(date));
        Headers headers = new Headers.Fake();
        DeliveryMode deliveryMode = new Persistent();

        RabbitProperties rabbitProperties = new RabbitProperties(delegate);

        AMQBasicProperties amqpProps = rabbitProperties.toAMQPProps(headers, deliveryMode);
        assertEquals(date, amqpProps.getTimestamp());
    }


}