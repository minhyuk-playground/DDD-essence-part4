package org.eternity.phone.billing.settlement.service;

import org.eternity.phone.billing.settlement.domain.PhoneBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneBillRepository extends JpaRepository<PhoneBill, Long> {
    Optional<PhoneBill> findByContractId(Long id);
}
