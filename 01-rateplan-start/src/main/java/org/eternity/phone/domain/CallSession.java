package org.eternity.phone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.eternity.phone.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.eternity.phone.domain.CallStatus.*;

@Entity
@AllArgsConstructor
public class CallSession extends AggregateRoot<CallSession, UUID> {
    @Id
    private UUID id;
    private String callingNumber;
    private String calledNumber;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private CallStatus status;

    @Override
    public UUID getId() {
        return id;
    }

    public CallSession connect(String callingNumber, String calledNumber, LocalDateTime startedTime) {
        return new CallSession(UUID.randomUUID(), callingNumber, calledNumber, startedTime, null, STARTED);
    }

    public CallRecord started() {
        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, STARTED, startedTime);
    }

    public CallRecord completed(LocalDateTime completedTime) {
        this.finishedTime = completedTime;
        this.status = COMPLETED;

        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, status, finishedTime);
    }

    public CallRecord failed(LocalDateTime failedTime) {
        this.finishedTime = failedTime;
        this.status = FAILED;

        return new CallRecord(UUID.randomUUID(), id, callingNumber, calledNumber, status, finishedTime);
    }
}
