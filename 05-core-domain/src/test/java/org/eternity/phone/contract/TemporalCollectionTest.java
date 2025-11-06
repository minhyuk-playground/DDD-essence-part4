package org.eternity.phone.contract;

import org.eternity.phone.shared.temporal.TemporalCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TemporalCollectionTest {
    private TemporalCollection<LocalDate, Integer> collection;

    @BeforeEach
    public void prepareTemporalCollection() {
        collection = new TemporalCollection<LocalDate, Integer>();
        collection.put(LocalDate.of(2025, 3, 1), 30);
        collection.put(LocalDate.of(2025, 4, 1), 40);
        collection.put(LocalDate.of(2025, 5, 1), 50);
    }

    @Test
    public void atCorrectData() {
        assertThat(collection.at(LocalDate.of(2025, 3, 1))).isEqualTo(30);
        assertThat(collection.at(LocalDate.of(2025, 4, 1))).isEqualTo(40);
        assertThat(collection.at(LocalDate.of(2025, 5, 1))).isEqualTo(50);
    }

    @Test
    public void atBeforeData() {
        assertThat(collection.at(LocalDate.of(2025, 2, 1))).isNull();
    }

    @Test
    public void atAfterData() {
        assertThat(collection.getEffectiveness(LocalDate.of(2025, 4, 11))).isEqualTo(40);
    }
}
