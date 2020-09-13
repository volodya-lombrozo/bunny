package org.lombrozo.bunny.address;

public class RealAddress implements Address {

    private final String host;
    private final int port;
    private final String virtualHost;

    public RealAddress(String host) {
        this(host, 5672);
    }

    public RealAddress(String host, int port) {
        this(host, port, "/");
    }

    public RealAddress(String host, int port, String virtualHost) {
        this.host = host;
        this.port = port;
        this.virtualHost = virtualHost;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public String host() {
        return host;
    }

    @Override
    public String virtualHost() {
        return virtualHost;
    }
}
