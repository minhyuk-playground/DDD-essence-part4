package org.eternity.phone.contract.domain;

import jakarta.persistence.*;
import org.eternity.phone.shared.domain.AggregateRoot;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.tracking.domain.CallRecord;

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

    public Money calculateFee(Collection<CallRecord[]> calls) {
        return calls.stream()
                    .map(call -> this.calculateCallFee(call[0], call[1]))
                    .reduce(Money.ZERO, Money::plus);
    }

    protected abstract Money calculateCallFee(CallRecord started, CallRecord completed);
}
