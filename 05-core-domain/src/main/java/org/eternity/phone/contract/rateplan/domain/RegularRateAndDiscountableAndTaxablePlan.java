package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("regular_discountable_taxable")
public class RegularRateAndDiscountableAndTaxablePlan extends RegularRateAndDiscountablePlan {
    private double taxRate;

    public RegularRateAndDiscountableAndTaxablePlan(Money amount, Duration duration, Money discountAmount, double taxRate) {
        super(amount, duration, discountAmount);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
