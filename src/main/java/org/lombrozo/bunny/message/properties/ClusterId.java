package org.lombrozo.bunny.message.properties;

public class ClusterId implements Property{

    private final String value;

    public ClusterId(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.CLUSTER_ID;
    }

    @Override
    public String value() {
        return value;
    }
}
