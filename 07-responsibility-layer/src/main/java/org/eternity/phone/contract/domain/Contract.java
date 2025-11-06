package org.eternity.phone.contract.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.eternity.phone.shared.domain.AggregateRoot;
import org.eternity.phone.shared.temporal.TimeInterval;

import java.time.LocalDate;

@Entity
@Getter
public class Contract extends AggregateRoot<Contract, Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Phone phone;
    private Long ratePlanId;
    private LocalDate contractDate;

    @AttributeOverrides({
        @AttributeOverride(name="start", column = @Column(name="start_date")),
        @AttributeOverride(name="end", column = @Column(name="end_date")),
    })
    private TimeInterval<LocalDate> period;

    public Contract(Phone phone, Long ratePlanId, LocalDate contractDate, TimeInterval<LocalDate> period) {
        this.phone = phone;
        this.ratePlanId = ratePlanId;
        this.contractDate = contractDate;
        this.period = period;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phone.getNumber();
    }
}
