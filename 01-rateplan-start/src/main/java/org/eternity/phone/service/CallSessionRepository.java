package org.eternity.phone.service;

import org.eternity.phone.domain.CallSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallSessionRepository extends JpaRepository<CallSession, UUID> {
}
