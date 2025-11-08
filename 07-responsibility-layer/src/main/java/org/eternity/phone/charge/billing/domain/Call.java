package org.eternity.phone.charge.billing.domain;

import org.eternity.phone.shared.temporal.TimeInterval;

import java.time.Duration;
import java.time.LocalDateTime;

public record Call(String phoneId, TimeInterval<LocalDateTime> period) {
    public LocalDateTime startTime() {
        return period.getStart();
    }

    public Duration duration() {
        return period.duration();
    }
}
