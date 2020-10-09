package org.lombrozo.bunny.domain.destination.reply;

public interface ReplyInfo {

    String exchange();

    String routingKey();

    String unitedString();
}
