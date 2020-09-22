package org.lombrozo.bunny.connection;

import java.lang.management.ManagementFactory;

public class PrefixNameStrategy implements ConnectionNameStrategy {

    private final String prefix;

    public PrefixNameStrategy() {
        this("");
    }

    public PrefixNameStrategy(String prefix) {
        this.prefix = prefix;
    }

    public String connectionName() {
        return prefix + "_" + ManagementFactory.getRuntimeMXBean().getName();
    }

}
