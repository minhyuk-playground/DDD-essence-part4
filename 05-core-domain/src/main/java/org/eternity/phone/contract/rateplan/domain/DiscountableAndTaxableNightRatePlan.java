package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("discountable_taxable_night")
public class DiscountableAndTaxableNightRatePlan extends TaxableNightRatePlan {
    private double taxRate;

    public DiscountableAndTaxableNightRatePlan(Money dayFee, Money nightAmount, Duration duration, double taxRate, double taxRate1) {
        super(dayFee, nightAmount, duration, taxRate);
        this.taxRate = taxRate1;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
