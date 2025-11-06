package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("taxable_discountable_regular")
public class TaxableAndDiscountableRegularRatePlan extends TaxableRegularRatePlan {
    @AttributeOverride(name="amount", column = @Column(name="DISCOUNT_AMOUNT"))
    private Money discountAmount;

    public TaxableAndDiscountableRegularRatePlan(Money amount, Duration duration, double taxRate, Money discountAmount) {
        super(amount, duration, taxRate);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).minus(discountAmount);
    }
}
