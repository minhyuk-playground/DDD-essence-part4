package org.eternity.phone.contract.billing.service;

import org.eternity.phone.contract.billing.domain.PhoneBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneBillRepository extends JpaRepository<PhoneBill, Long> {
    Optional<PhoneBill> findByContractId(Long id);
}
