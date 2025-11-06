package org.eternity.phone.service;

import org.eternity.phone.domain.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallRecordRepository extends JpaRepository<CallRecord, UUID> {
}
