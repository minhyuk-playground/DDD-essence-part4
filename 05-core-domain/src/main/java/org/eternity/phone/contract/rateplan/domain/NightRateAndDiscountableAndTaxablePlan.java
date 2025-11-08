package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("night_discountable_taxable")
public class NightRateAndDiscountableAndTaxablePlan extends NightRateAndDiscountablePlan {
    private double taxRate;

    public NightRateAndDiscountableAndTaxablePlan(Money dayFee, Money nightAmount, Duration duration, Money discountAmount, double taxRate1) {
        super(dayFee, nightAmount, duration, discountAmount);
        this.taxRate = taxRate1;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
