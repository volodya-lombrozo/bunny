package org.lombrozo.bunny.util.connection;

import org.junit.Test;
import org.lombrozo.bunny.connection.PrefixNameStrategy;

import static org.junit.Assert.*;

public class PrefixNameStrategyTest {

    @Test
    public void connectionName_prefix() {
        String prefix = "prefix";
        PrefixNameStrategy strategy = new PrefixNameStrategy(prefix);

        String connectionName = strategy.connectionName();

        assertTrue(connectionName.startsWith(prefix));
    }


    @Test
    public void connectionName_emptyPrefix() {
        PrefixNameStrategy strategy = new PrefixNameStrategy();

        String connectionName = strategy.connectionName();

        assertNotNull(connectionName);
    }
}