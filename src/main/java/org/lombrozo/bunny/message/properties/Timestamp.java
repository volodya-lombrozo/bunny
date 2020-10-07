package org.lombrozo.bunny.message.properties;


import java.util.Date;

public class Timestamp implements Property {

    private final Date value;

    public Timestamp(Date date) {
        this.value = date;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.TIMESTAMP;
    }

    @Override
    public String value() {
        if (value == null) return "";
        return String.valueOf(value.toInstant().toEpochMilli());
    }

}
