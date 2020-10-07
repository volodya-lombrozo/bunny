package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClusterIdTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property clusterId = new ClusterId(expected);

        assertEquals(expected, clusterId.value());
        assertEquals(PropertyKey.CLUSTER_ID, clusterId.key());
    }



}