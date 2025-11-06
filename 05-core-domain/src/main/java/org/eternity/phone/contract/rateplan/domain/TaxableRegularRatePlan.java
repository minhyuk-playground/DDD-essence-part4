package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.eternity.phone.shared.monetary.Money;

import java.time.Duration;

@Entity
@DiscriminatorValue("taxable_regular")
public class TaxableRegularRatePlan extends RegularRatePlan {
    private double taxRate;

    public TaxableRegularRatePlan(Money amount, Duration duration, double taxRate) {
        super(amount, duration);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }
}
