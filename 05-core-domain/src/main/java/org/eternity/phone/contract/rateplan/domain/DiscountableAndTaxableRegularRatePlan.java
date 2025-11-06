package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("discountable_taxable_regular")
public class DiscountableAndTaxableRegularRatePlan extends DiscountableRegularRatePlan {
    private double taxRate;

    public DiscountableAndTaxableRegularRatePlan(Money amount, Duration duration, Money discountAmount, double taxRate) {
        super(amount, duration, discountAmount);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
