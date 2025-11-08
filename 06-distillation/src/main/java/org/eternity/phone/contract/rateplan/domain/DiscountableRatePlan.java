package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.eternity.phone.shared.monetary.Money;

@Entity
@DiscriminatorValue("discount")
@NoArgsConstructor
public class DiscountableRatePlan extends AdditionalRatePlan {
    private Money discountAmount;

    public DiscountableRatePlan(Money discountAmount) {
        this(discountAmount, null);
    }

    public DiscountableRatePlan(Money discountAmount, AdditionalRatePlan next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money apply(Money fee) {
        return fee.minus(discountAmount);
    }
}
