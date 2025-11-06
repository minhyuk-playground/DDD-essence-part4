package org.eternity.phone.contract.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.shared.temporal.TimeInterval;
import org.eternity.phone.tracking.domain.CallRecord;

import java.time.Duration;

@Entity
@DiscriminatorValue("regular")
public class RegularRatePlan extends RatePlan {
    private Money amount;
    private Duration duration;

    public RegularRatePlan(Money amount, Duration duration) {
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    protected Money calculateCallFee(CallRecord started, CallRecord completed) {
        return amount.times(TimeInterval.of(started.getOccurredTime(), completed.getOccurredTime()).duration().dividedBy(duration));
    }
}
