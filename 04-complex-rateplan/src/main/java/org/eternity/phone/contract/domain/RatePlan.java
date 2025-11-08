package org.eternity.phone.contract.domain;

import jakarta.persistence.*;
import org.eternity.phone.shared.domain.AggregateRoot;
import org.eternity.phone.shared.monetary.Money;

import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "plan_type")
public abstract class RatePlan extends AggregateRoot<RatePlan, Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public Money calculateFee(Collection<Call> calls) {
        Money fee = calls.stream()
                        .map(call -> this.calculateCallFee(call))
                        .reduce(Money.ZERO, Money::plus);

        return afterCalculated(fee);
    }

    protected Money afterCalculated(Money fee) {
        return fee;
    }

    protected abstract Money calculateCallFee(Call call);
}
