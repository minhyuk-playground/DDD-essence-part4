package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("taxable_night")
public class TaxableNightRatePlan extends NightRatePlan {
    private double taxRate;

    public TaxableNightRatePlan(Money dayFee, Money nightAmount, Duration duration, double taxRate) {
        super(dayFee, nightAmount, duration);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
