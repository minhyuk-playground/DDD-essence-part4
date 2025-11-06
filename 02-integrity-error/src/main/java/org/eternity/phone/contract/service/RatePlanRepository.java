package org.eternity.phone.contract.service;

import org.eternity.phone.contract.domain.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}
