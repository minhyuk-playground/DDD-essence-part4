package org.eternity.phone.charge.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.charge.billing.domain.Call;
import org.eternity.phone.shared.monetary.Money;

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

    public RegularRatePlan(Money amount, Duration duration, AdditionalRatePlan additionalRatePlan) {
        super(additionalRatePlan);
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times(call.duration().dividedBy(duration));
    }
}
