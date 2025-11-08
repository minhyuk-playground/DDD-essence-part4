package org.eternity.phone.contract.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("regular_taxable_discountable")
public class RegularRateAndTaxableAndDiscountablePlan extends RegularRateAndTaxablePlan {
    @AttributeOverride(name="amount", column = @Column(name="DISCOUNT_AMOUNT"))
    private Money discountAmount;

    public RegularRateAndTaxableAndDiscountablePlan(Money amount, Duration duration, double taxRate, Money discountAmount) {
        super(amount, duration, taxRate);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).minus(discountAmount);
    }
}
