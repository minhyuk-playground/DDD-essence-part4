package org.eternity.phone.billing.settlement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eternity.phone.shared.domain.AggregateRoot;
import org.eternity.phone.shared.monetary.Money;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class PhoneBill extends AggregateRoot<PhoneBill, Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;
    private Phone phone;
    private Money fee;

    public PhoneBill(Long contractId, Phone phone, Money fee) {
        this.contractId = contractId;
        this.phone = phone;
        this.fee = fee;
    }
}
