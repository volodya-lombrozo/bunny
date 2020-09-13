package org.lombrozo.bunny.util.connection;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomConnectionName implements ConnectionNameStrategy {

    public int nameLength;

    public RandomConnectionName() {
        this(16);
    }

    public RandomConnectionName(int nameLength) {
        this.nameLength = nameLength;
    }

    public String connectionName() {
        byte[] array = new byte[nameLength];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
