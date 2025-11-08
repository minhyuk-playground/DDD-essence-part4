package org.eternity.phone.service;

import org.eternity.phone.domain.CallRecord;
import org.eternity.phone.domain.CallStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CallDTO(UUID callId, UUID sessionId, String callingNumber, String calledNumber,
                      CallStatus status, LocalDateTime occurredTime) {
    public CallRecord asCallRecord() {
        return new CallRecord(callId, sessionId, calledNumber, calledNumber, status, occurredTime);
    }
}
