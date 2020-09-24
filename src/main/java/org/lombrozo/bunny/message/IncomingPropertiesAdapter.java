package org.lombrozo.bunny.message;

import com.rabbitmq.client.AMQP;

public class IncomingPropertiesAdapter {

    private final AMQP.BasicProperties properties;

    public IncomingPropertiesAdapter(AMQP.BasicProperties properties) {
        this.properties = properties;
    }

    public Properties toProperties() {
        Properties res = new PropertiesSet();
        String correlationId = properties.getCorrelationId();
        if (correlationId != null && !correlationId.isEmpty())
            res.put(new CorrelationId(correlationId));
        String replyTo = properties.getReplyTo();
        if (replyTo != null && !replyTo.isEmpty())
            res.put(new ReplyTo(replyTo));
        String type = properties.getType();
        if (type != null && !type.isEmpty())
            res.put(new Type(type));
        return res;
    }
}
