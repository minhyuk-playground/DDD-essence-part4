package org.eternity.phone.contract.rateplan.service;

import org.eternity.phone.contract.rateplan.domain.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}
