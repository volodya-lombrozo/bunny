package org.lombrozo.bunny.util.connection;

import org.junit.Test;
import org.lombrozo.bunny.connection.RandomConnectionName;

import static org.junit.Assert.*;

public class RandomConnectionNameTest {

    @Test
    public void connectionName() {
        RandomConnectionName connectionNameStrategy = new RandomConnectionName();

        String connectionName = connectionNameStrategy.connectionName();

        assertNotNull(connectionName);
    }
}