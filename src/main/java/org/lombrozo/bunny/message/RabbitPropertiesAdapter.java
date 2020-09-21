package org.lombrozo.bunny.message;


import com.rabbitmq.client.AMQP;


public class RabbitPropertiesAdapter {

    private final Properties properties;

    public RabbitPropertiesAdapter(Properties properties) {
        this.properties = properties;
    }

    public AMQP.BasicProperties toRabbitProperties() {
        String property = properties.property(new CorrelationId().key());
        return new AMQP.BasicProperties.Builder()
                .correlationId(property)
                .build();
    }


}
