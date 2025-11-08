package org.eternity.phone.tracking.service;

import org.eternity.phone.tracking.domain.CallRecord;
import org.eternity.phone.tracking.domain.CallPhase;
import org.eternity.phone.tracking.domain.CallStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CallDTO(UUID callId, UUID sessionId, String callingNumber, String calledNumber,
                      CallPhase phase, CallStatus status, LocalDateTime occurredTime) {
    public CallRecord asCallRecord() {
        return new CallRecord(callId, sessionId, calledNumber, calledNumber, phase, status, occurredTime);
    }
}
