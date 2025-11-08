package org.eternity.phone.tracking.service;

import org.assertj.core.groups.Tuple;
import org.eternity.phone.tracking.domain.CallRecord;
import org.eternity.phone.tracking.service.CallRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.eternity.phone.tracking.domain.CallPhase.*;
import static org.eternity.phone.tracking.domain.CallStatus.*;

@DataJpaTest(showSql = false)
public class CallRecordRepositoryTest {
    @Autowired
    private CallRecordRepository callRecordRepository;

    @Test
    @DisplayName("요금 계산을 위한 통화 목록 조회")
    public void call_load() {
        UUID[] callIds = { UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                           UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() };
        UUID[] sessionIds = { UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() };

        callRecordRepository.saveAllAndFlush(
                List.of(
                        new CallRecord(callIds[0], sessionIds[0], "010-1111-2222", "010-3333-4444", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 0)),
                        new CallRecord(callIds[3], sessionIds[1], "010-1111-2222", "010-5555-6666", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 30)),
                        new CallRecord(callIds[2], sessionIds[1], "010-1111-2222", "010-5555-6666", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 20)),
                        new CallRecord(callIds[1], sessionIds[0], "010-1111-2222", "010-3333-4444", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 10)),
                        new CallRecord(callIds[4], sessionIds[2], "010-1111-2222", "010-7777-8888", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 40)),
                        new CallRecord(callIds[5], sessionIds[2], "010-1111-2222", "010-7777-8888", FAILED, NETWORK_FAILURE, LocalDateTime.of(2025, 1, 1, 10, 0, 50))
                ));

        Collection<CallRecord[]> calls = callRecordRepository.findCallRecordsToBill("010-1111-2222");

        assertThatCollection(calls)
                .map(record -> new Tuple(record[0].getId(), record[1].getId()))
                .contains(new Tuple(callIds[0],callIds[1]), new Tuple(callIds[2], callIds[3]));
    }
}
