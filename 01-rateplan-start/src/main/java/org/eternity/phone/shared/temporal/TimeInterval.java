package org.eternity.phone.shared.temporal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Duration;
import java.time.temporal.Temporal;

@Embeddable
@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
public class TimeInterval<T extends Temporal & Comparable<? super T>> {
    @Column(name="start_time") private T start;
    @Column(name="end_time") private T end;

    public static <T extends Temporal & Comparable<? super T>> TimeInterval of(T start, T end) {
        return new TimeInterval(start, end);
    }

    public boolean include(T time) {
        return time.compareTo(start) >= 0 || time.compareTo(end) <= 0;
    }

    public Duration duration() {
        return Duration.between(start, end);
    }
}
