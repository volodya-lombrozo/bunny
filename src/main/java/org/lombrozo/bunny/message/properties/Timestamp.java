package org.lombrozo.bunny.message.properties;


import java.util.Date;

public class Timestamp implements Property {

    private final String value;


    public Timestamp(Date date) {
        this(String.valueOf(date.toInstant().toEpochMilli()));
    }

    public Timestamp(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.TIMESTAMP;
    }

    @Override
    public String value() {
        return value;
    }

}
