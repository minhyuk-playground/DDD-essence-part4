package org.eternity.phone.shared.temporal;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.reverseOrder;

@Embeddable
@EqualsAndHashCode
public class TemporalCollection<T extends ChronoLocalDate, V> {
    @ElementCollection
    private Map<T, V> contents = new HashMap<>();

    public void put(T at, V property) {
        contents.put(at, property);
    }

    public boolean contains(T key) {
        return contents.get(key) != null;
    }

    public V at(T key) {
        return contents.get(key);
    }

    public V getEffectiveness(T key) {
        for (T current : sortedKeys()) {
            if (current.compareTo(key) < 0 || current.equals(key)) {
                return contents.get(current);
            }
        }

        return null;
    }

    private List<T> sortedKeys() {
        return contents.keySet().stream().sorted(reverseOrder()).toList();
    }

    @Override
    public String toString() {
        return contents.toString();
    }
}
