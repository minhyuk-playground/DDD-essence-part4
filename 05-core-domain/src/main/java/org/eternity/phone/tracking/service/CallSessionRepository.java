package org.eternity.phone.tracking.service;

import org.eternity.phone.tracking.domain.CallSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallSessionRepository extends JpaRepository<CallSession, UUID> {
}
