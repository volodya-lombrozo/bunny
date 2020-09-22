package org.lombrozo.bunny.connection;

public interface ConnectionNameStrategy {

    String connectionName();

    class Fake implements ConnectionNameStrategy {
        public String connectionName() {
            return "";
        }
    }

}
