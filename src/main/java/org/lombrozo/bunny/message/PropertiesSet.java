package org.lombrozo.bunny.message;

import java.util.*;
import java.util.stream.Collectors;

public class PropertiesSet implements Properties {

    private final Map<PropertyKey, Property> properties;

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
    public String property(PropertyKey key) {
        return properties.getOrDefault(key, new Property.Empty()).value();
    }

    @Override
    public void put(Property property) {
        properties.put(property.key(), property);
    }

    @Override
    public boolean containsProperty(PropertyKey key) {
        return properties.containsKey(key);
    }

    @Override
    public Properties addAll(Property[] additional) {
        for (Property property : additional) put(property);
        return this;
    }


    @Override
    public String toString() {
        return "PropertiesSet{" +
                "properties=" + properties +
                '}';
    }
}
