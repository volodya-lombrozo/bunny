package org.lombrozo.bunny.domain.destination.reply;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class ReplyDestination implements Destination {

    private final ReplyInfo replyInfo;

    public ReplyDestination(String rawString) {
        this(new DotReplyInfo(rawString));
    }

    public ReplyDestination(ReplyInfo replyInfo) {
        this.replyInfo = replyInfo;
    }

    @Override
    public String exchangeName() {
        return replyInfo.exchange();
    }

    @Override
    public String routingKey() {
        return replyInfo.routingKey();
    }

}
