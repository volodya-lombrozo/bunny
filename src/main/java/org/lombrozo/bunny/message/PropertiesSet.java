package org.lombrozo.bunny.message;

import java.util.*;
import java.util.stream.Collectors;

public class PropertiesSet implements Properties {

    Map<String, Property> properties;

    public PropertiesSet() {
        this(new HashSet<>());
    }

    public PropertiesSet(Property... properties) {
        this(Arrays.asList(properties));
    }

    public PropertiesSet(Collection<Property> properties) {
        this.properties = properties.stream().collect(Collectors.toMap(Property::key, property -> property));
    }

    @Override
    public String property(String key) {
        return properties.getOrDefault(key, new Property.Empty()).value();
    }

    @Override
    public void put(Property property) {
        properties.put(property.key(), property);
    }


    @Override
    public String toString() {
        return "PropertiesSet{" +
                "properties=" + properties +
                '}';
    }
}
