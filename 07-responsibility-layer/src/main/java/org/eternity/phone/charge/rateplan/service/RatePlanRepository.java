package org.eternity.phone.charge.rateplan.service;

import org.eternity.phone.charge.rateplan.domain.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}
