package org.eternity.phone.contract.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("regular_discountable")
public class RegularRateAndDiscountablePlan extends RegularRatePlan {
    @AttributeOverride(name="amount", column = @Column(name="DISCOUNT_AMOUNT"))
    private Money discountAmount;

    public RegularRateAndDiscountablePlan(Money amount, Duration duration, Money discountAmount) {
        super(amount, duration);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).minus(discountAmount);
    }
}
