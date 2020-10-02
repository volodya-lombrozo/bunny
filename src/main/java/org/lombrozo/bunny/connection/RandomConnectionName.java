package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.util.RandomString;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomConnectionName implements ConnectionNameStrategy {

    private final int nameLength;

    public RandomConnectionName() {
        this(16);
    }

    public RandomConnectionName(int nameLength) {
        this.nameLength = nameLength;
    }

    public String connectionName() {
        return "Connection#" + new RandomString(nameLength).toString();
    }
}
