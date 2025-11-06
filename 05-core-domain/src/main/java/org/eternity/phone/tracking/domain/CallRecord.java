package org.eternity.phone.tracking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eternity.phone.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class CallRecord extends AggregateRoot<CallRecord, UUID> {
    @Id
    private UUID id;
    private UUID sessionId;

    private String callingNumber;
    private String calledNumber;

    @Enumerated(EnumType.STRING)
    private CallPhase phase;

    @Enumerated(EnumType.STRING)
    private CallStatus status;

    private LocalDateTime occurredTime;
}
