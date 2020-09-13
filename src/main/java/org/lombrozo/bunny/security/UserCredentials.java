package org.lombrozo.bunny.security;

public class UserCredentials implements Credentials {

    private final String username;
    private final String password;


    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
