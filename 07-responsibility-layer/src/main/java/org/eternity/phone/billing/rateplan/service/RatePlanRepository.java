package org.eternity.phone.billing.rateplan.service;

import org.eternity.phone.billing.rateplan.domain.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}
