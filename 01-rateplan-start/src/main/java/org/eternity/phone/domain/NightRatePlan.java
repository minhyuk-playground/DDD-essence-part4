package org.eternity.phone.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.shared.temporal.TimeInterval;

import java.time.Duration;
import java.time.LocalTime;

@Entity
@DiscriminatorValue("night")
public class NightRatePlan extends RatePlan {
    private static final TimeInterval<LocalTime> NIGHT = TimeInterval.of(LocalTime.of(22, 0), LocalTime.of(6, 0));

    @AttributeOverride(name="amount", column = @Column(name="night_amount"))
    private Money nightAmount;
    @AttributeOverride(name="amount", column = @Column(name="day_amount"))
    private Money dayAmount;

    private Duration duration;

    public NightRatePlan(Money dayFee, Money nightAmount, Duration duration) {
        this.dayAmount = dayFee;
        this.nightAmount = nightAmount;
        this.duration = duration;
    }

    @Override
    protected Money calculateCallFee(CallRecord started, CallRecord completed) {
        Duration callDuration = TimeInterval.of(started.getOccurredTime(), completed.getOccurredTime()).duration();
        if (NIGHT.include(started.getOccurredTime().toLocalTime())) {
            return nightAmount.times(callDuration.dividedBy(duration));
        }

        return dayAmount.times(callDuration.dividedBy(duration));
    }
}
