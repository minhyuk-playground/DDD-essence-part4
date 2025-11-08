package org.eternity.phone.contract.rateplan.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.eternity.phone.contract.billing.domain.Call;
import org.eternity.phone.shared.monetary.Money;

import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "plan_type")
@NoArgsConstructor
public abstract class RatePlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private AdditionalRatePlan additionalRatePlan;

    public RatePlan(AdditionalRatePlan additionalRatePlan) {
        this.additionalRatePlan = additionalRatePlan;
    }

    public Long getId() {
        return id;
    }

    public Money calculateFee(Collection<Call> calls) {
        Money fee = calls.stream()
                    .map(call -> this.calculateCallFee(call))
                    .reduce(Money.ZERO, Money::plus);

        if (additionalRatePlan != null) {
            return additionalRatePlan.calculate(fee);
        }

        return fee;
    }

    protected abstract Money calculateCallFee(Call call);
}
