package org.lombrozo.bunny.util.connection;

public interface ConnectionNameStrategy {

    String connectionName();

    class Fake implements ConnectionNameStrategy {
        public String connectionName() {
            return "";
        }
    }

}
