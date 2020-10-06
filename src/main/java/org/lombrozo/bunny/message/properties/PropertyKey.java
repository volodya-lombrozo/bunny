package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.impl.AMQBasicProperties;

import java.util.function.Function;

public enum PropertyKey {
    CONTENT_TYPE("content_type", amqp -> new ContentType(amqp.getContentType())),
    CONTENT_ENCODING("content_encoding", amqp -> new ContentEncoding(amqp.getContentEncoding())),
    PRIORITY("priority", amqp -> new Priority(amqp.getPriority())),
    CORRELATION_ID("correlation_id", amqp -> new CorrelationId(amqp.getCorrelationId())),
    REPLY_TO("reply_to", amqp -> new ReplyTo(amqp.getReplyTo())),
    EXPIRATION("expiration", amqp -> new Expiration(amqp.getExpiration())),
    MESSAGE_ID("message_id", amqp -> new MessageId(amqp.getMessageId())),
    TIMESTAMP("timestamp", amqp -> new Timestamp(amqp.getTimestamp())),
    TYPE("type", amqp -> new Type(amqp.getType())),
    USER_ID("user_id", amqp -> new UserId(amqp.getUserId())),
    APP_ID("app_id", amqp -> new AppId(amqp.getAppId())),
    CLUSTER_ID("cluster_id", amqp -> new UnknownProperty("Property cluster_id unsupported by rabbit_client library")),
    UNKNOWN("", amqp -> new UnknownProperty("Unknown property"));

    private final String key;
    private final Function<AMQBasicProperties, Property> propertyExtractor;

    PropertyKey(String key, Function<AMQBasicProperties, Property> extractor) {
        this.key = key;
        this.propertyExtractor = extractor;
    }

    public Property toProperty(AMQBasicProperties value) {
        return propertyExtractor.apply(value);
    }

    @Override
    public String toString() {
        return key;
    }
}
