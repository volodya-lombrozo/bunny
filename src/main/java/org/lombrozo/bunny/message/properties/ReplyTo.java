package org.lombrozo.bunny.message.properties;


import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.destination.reply.DotReplyInfo;
import org.lombrozo.bunny.domain.destination.reply.ReplyInfo;
import org.lombrozo.bunny.domain.queue.Queue;

public class ReplyTo implements ReplyProperty {

    private final String value;

    public ReplyTo() {
        this("");
    }

    public ReplyTo(Queue queue) {
        this(new QueueDestination(queue));
    }

    public ReplyTo(Destination destination) {
        this(new DotReplyInfo(destination));
    }

    public ReplyTo(ReplyInfo replyInfo) {
        this(replyInfo.unitedString());
    }

    public ReplyTo(String destination) {
        this.value = destination;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.REPLY_TO;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "ReplyTo{" +
                "value='" + value + '\'' +
                '}';
    }
}
