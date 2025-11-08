package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("night_discountable")
public class NightRateAndDiscountablePlan extends NightRatePlan {
    @AttributeOverride(name="amount", column = @Column(name="DISCOUNT_AMOUNT"))
    private Money discountAmount;

    public NightRateAndDiscountablePlan(Money dayFee, Money nightAmount, Duration duration, Money discountAmount) {
        super(dayFee, nightAmount, duration);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).minus(discountAmount);
    }
}
