package org.eternity.phone.charge.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.eternity.phone.shared.monetary.Money;

@Entity
@DiscriminatorValue("tax")
@NoArgsConstructor
public class TaxableRatePlan extends AdditionalRatePlan {
    private double taxRate;

    public TaxableRatePlan(double taxRate) {
        this(taxRate, null);
    }

    public TaxableRatePlan(double taxRate, AdditionalRatePlan next) {
        super(next);
        this.taxRate = taxRate;
    }

    @Override
    protected Money apply(Money fee) {
        return fee.plus(fee.times(taxRate));
    }
}
