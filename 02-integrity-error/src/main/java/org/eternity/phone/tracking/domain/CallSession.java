package org.eternity.phone.tracking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.eternity.phone.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.eternity.phone.tracking.domain.CallPhase.*;
import static org.eternity.phone.tracking.domain.CallStatus.IN_PROGRESS;


@Entity
@AllArgsConstructor
public class CallSession extends AggregateRoot<CallSession, UUID> {
    @Id
    private UUID id;
    private String callingNumber;
    private String calledNumber;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private CallPhase phase;

    @Override
    public UUID getId() {
        return id;
    }
    public CallSession connect(String callingNumber, String calledNumber, LocalDateTime startedTime) {
        return new CallSession(UUID.randomUUID(), callingNumber, calledNumber, startedTime, null, STARTED);
    }

    public CallRecord started() {
        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, STARTED, IN_PROGRESS, startedTime);
    }

    public CallRecord completed(LocalDateTime completedTime, CallStatus status) {
        this.finishedTime = completedTime;
        this.phase = COMPLETED;

        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, COMPLETED, status, finishedTime);
    }

    public CallRecord failed(LocalDateTime failedTime, CallStatus status) {
        this.finishedTime = failedTime;
        this.phase = FAILED;

        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, FAILED, status, finishedTime);
    }
}
