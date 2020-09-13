package org.lombrozo.bunny.security;

public interface Credentials {

    String username();

    String password();


    class Fake implements Credentials{


        public String username() {
            return "";
        }

        public String password() {
            return "";
        }
    }

}
