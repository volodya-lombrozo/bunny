package org.lombrozo.bunny.message;


import com.rabbitmq.client.AMQP;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.Properties;
import org.lombrozo.bunny.message.properties.PropertyKey;


public class OutboxPropertiesAdapter {

    private final Properties properties;
    private final Headers headers;

    public OutboxPropertiesAdapter(Properties properties, Headers headers) {
        this.properties = properties;
        this.headers = headers;
    }

    public AMQP.BasicProperties toRabbitProperties() {
        String correlationId = properties.property(PropertyKey.CORRELATION_ID);
        String replyTo = properties.property(PropertyKey.REPLY_TO);
        String type = properties.property(PropertyKey.TYPE);
        String contentType = properties.property(PropertyKey.CONTENT_TYPE);
        return new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .replyTo(replyTo)
                .contentType(contentType)
                .type(type)
                .headers(headers.toMap())
                .build();
    }


}
