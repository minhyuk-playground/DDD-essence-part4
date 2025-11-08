package org.eternity.phone.contract.domain;

import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.tracking.domain.CallRecord;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eternity.phone.tracking.domain.CallPhase.*;
import static org.eternity.phone.tracking.domain.CallStatus.*;

public class NightRatePlanTest {
    @Test
    public void 심야요금제_요금_계산() {
        UUID sessionId1 = UUID.randomUUID();
        UUID sessionId2 = UUID.randomUUID();

        List<CallRecord[]> calls = List.of(
            new CallRecord[]{
                new CallRecord(UUID.randomUUID(), sessionId1, "010-1111-2222", "010-3333-4444", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 18, 30, 0)),
                new CallRecord(UUID.randomUUID(), sessionId1, "010-1111-2222", "010-3333-4444", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 18, 30, 10))
            },
            new CallRecord[]{
                new CallRecord(UUID.randomUUID(), sessionId2, "010-1111-2222", "010-5555-6666", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 23, 0, 20)),
                new CallRecord(UUID.randomUUID(), sessionId2, "010-1111-2222", "010-5555-6666", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 23, 0, 30))
            });

        NightRatePlan plan = new NightRatePlan(Money.won(10), Money.won(1), Duration.ofSeconds(10));

        Money fee = plan.calculateFee(calls);
        assertThat(fee).isEqualTo(Money.won(11));
    }
}
