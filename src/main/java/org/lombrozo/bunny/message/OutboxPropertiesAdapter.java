package org.lombrozo.bunny.message;


import com.rabbitmq.client.AMQP;
import org.lombrozo.bunny.message.header.Headers;


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
        return new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .replyTo(replyTo)
                .type(type)
                .headers(headers.toMap())
                .build();
    }


}
