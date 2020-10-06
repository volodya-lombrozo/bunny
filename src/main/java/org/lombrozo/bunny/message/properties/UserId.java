package org.lombrozo.bunny.message.properties;

public class UserId implements Property {

    private final String userId;

    public UserId(String userId) {
        this.userId = userId;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.USER_ID;
    }

    @Override
    public String value() {
        return userId;
    }
}
