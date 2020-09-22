package org.lombrozo.bunny.message;


import com.rabbitmq.client.AMQP;


public class OutboxPropertiesAdapter {

    private final Properties properties;

    public OutboxPropertiesAdapter(Properties properties) {
        this.properties = properties;
    }

    public AMQP.BasicProperties toRabbitProperties() {
        String correlationId = properties.property(PropertyKey.CORRELATION_ID);
        String replyTo = properties.property(PropertyKey.REPLY_TO);
        return new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .replyTo(replyTo)
                .build();
    }


}
