package org.eternity.phone.tracking.service;

import org.eternity.phone.tracking.domain.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallRecordRepository extends JpaRepository<CallRecord, UUID> {
}
