package org.lombrozo.bunny.util.reply;

import org.lombrozo.bunny.domain.Destination;

public class DotReplyToFormat implements ReplyToFormat {

    private final String rawString;

    public DotReplyToFormat(String rawString) {
        this.rawString = rawString;
    }

    @Override
    public Destination destination() {
        return null;
    }
}
