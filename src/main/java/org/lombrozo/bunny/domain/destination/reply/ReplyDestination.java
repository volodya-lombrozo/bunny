package org.lombrozo.bunny.domain.destination.reply;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class ReplyDestination implements Destination {

    private final ReplyInfo replyInfo;
    private final Connection connection;

    public ReplyDestination(String rawString, Connection connection) {
        this(new DotReplyInfo(rawString), connection);
    }

    public ReplyDestination(ReplyInfo replyInfo, Connection connection) {
        this.replyInfo = replyInfo;
        this.connection = connection;
    }

    @Override
    public String exchangeName() {
        return replyInfo.exchange();
    }

    @Override
    public String routingKey() {
        return replyInfo.routingKey();
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }

}
