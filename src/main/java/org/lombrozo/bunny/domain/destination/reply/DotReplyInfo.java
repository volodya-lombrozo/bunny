package org.lombrozo.bunny.domain.destination.reply;

import org.lombrozo.bunny.domain.destination.Destination;

public class DotReplyInfo implements ReplyInfo {

    private final String rawString;

    public DotReplyInfo(String rawString) {
        this.rawString = rawString;
    }

    public DotReplyInfo(Destination destination) {
        this(destination.exchangeName() + "." + destination.routingKey());
    }

    @Override
    public String exchange() {
        String[] split = rawString.split("\\.");
        return split[0];
    }

    @Override
    public String routingKey() {
        String[] split = rawString.split("\\.");
        if (split.length > 1)
            return split[1];
        else return "";
    }

    @Override
    public String unitedString() {
        return rawString;
    }

}
