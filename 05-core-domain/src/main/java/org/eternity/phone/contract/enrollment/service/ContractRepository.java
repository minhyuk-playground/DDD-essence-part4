package org.eternity.phone.contract.enrollment.service;

import org.eternity.phone.contract.enrollment.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
