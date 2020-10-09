package org.lombrozo.bunny.message.delivery;

public class Persistent implements DeliveryMode {

    @Override
    public String toString() {
        return "Persistent{}";
    }

    @Override
    public int toInt() {
        return 2;
    }
}
