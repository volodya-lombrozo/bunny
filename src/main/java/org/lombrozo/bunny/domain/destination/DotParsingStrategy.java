package org.lombrozo.bunny.domain.destination;

public class DotParsingStrategy implements ReplyParsingStrategy {

    @Override
    public String exchange(String rawString) {
        String[] split = rawString.split("\\.");
        return split[0];
    }

    @Override
    public String routingKey(String rawString) {
        String[] split = rawString.split("\\.");
        if (split.length > 1)
            return split[1];
        else return "";
    }

}
