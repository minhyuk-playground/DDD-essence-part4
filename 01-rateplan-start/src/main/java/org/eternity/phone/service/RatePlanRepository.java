package org.eternity.phone.service;

import org.eternity.phone.domain.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}
