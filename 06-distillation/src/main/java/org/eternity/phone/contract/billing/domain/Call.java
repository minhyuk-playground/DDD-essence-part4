package org.eternity.phone.contract.billing.domain;

import org.eternity.phone.shared.temporal.TimeInterval;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record Call(String phoneNumber, TimeInterval<LocalDateTime> period) {
    public Duration duration() {
        return period.duration();
    }

    public LocalTime startTime() {
        return period.getStart().toLocalTime();
    }

    public LocalTime completedTime() {
        return period.getEnd().toLocalTime();
    }
}
