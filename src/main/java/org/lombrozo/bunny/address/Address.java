package org.lombrozo.bunny.address;

public interface Address {

    int port();

    String host();

    String virtualHost();

    class FakeAddress implements Address{

        public int port() {
            return 0;
        }

        public String host() {
            return "";
        }

        public String virtualHost() {
            return "/";
        }
    }

}
