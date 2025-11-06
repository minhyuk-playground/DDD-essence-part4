package org.eternity.phone.contract;

import org.eternity.phone.contract.domain.RegularRatePlan;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.tracking.domain.CallRecord;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eternity.phone.tracking.domain.CallPhase.COMPLETED;
import static org.eternity.phone.tracking.domain.CallPhase.STARTED;
import static org.eternity.phone.tracking.domain.CallStatus.IN_PROGRESS;
import static org.eternity.phone.tracking.domain.CallStatus.NORMAL_CLEARING;

public class RegularRatePlanTest {
    @Test
    public void 일반요금제_요금_계산() {
        UUID sessionId1 = UUID.randomUUID();
        UUID sessionId2 = UUID.randomUUID();

        List<CallRecord[]> calls = List.of(
                new CallRecord[]{
                    new CallRecord(UUID.randomUUID(), sessionId1, "010-1111-2222", "010-3333-4444", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 0)),
                    new CallRecord(UUID.randomUUID(), sessionId1, "010-1111-2222", "010-3333-4444", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 10))
                },
                new CallRecord[]{
                    new CallRecord(UUID.randomUUID(), sessionId2, "010-1111-2222", "010-5555-6666", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 20)),
                    new CallRecord(UUID.randomUUID(), sessionId2, "010-1111-2222", "010-5555-6666", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 30))
                });

        RegularRatePlan plan = new RegularRatePlan(Money.won(10), Duration.ofSeconds(10));

        Money fee = plan.calculateFee(calls);
        assertThat(fee).isEqualTo(Money.won(20));
    }
}
