package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("night_taxable")
public class NightRateAndTaxablePlan extends NightRatePlan {
    private double taxRate;

    public NightRateAndTaxablePlan(Money dayFee, Money nightAmount, Duration duration, double taxRate) {
        super(dayFee, nightAmount, duration);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
