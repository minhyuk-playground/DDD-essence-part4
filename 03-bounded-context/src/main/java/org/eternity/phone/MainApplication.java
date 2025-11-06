package org.eternity.phone;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.eternity.phone.contract.domain.Contract;
import org.eternity.phone.contract.domain.Phone;
import org.eternity.phone.contract.domain.RatePlan;
import org.eternity.phone.contract.domain.RegularRatePlan;
import org.eternity.phone.contract.service.PhoneBillRepository;
import org.eternity.phone.contract.service.SettlementService;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.shared.temporal.TimeInterval;
import org.eternity.phone.tracking.domain.CallRecord;
import org.eternity.phone.tracking.service.CallRecordRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.eternity.phone.tracking.domain.CallPhase.*;
import static org.eternity.phone.tracking.domain.CallStatus.*;

@Slf4j
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(
            EntityManager em,
            TransactionTemplate template,
            CallRecordRepository callRecordRepository,
            SettlementService settlementService,
            PhoneBillRepository phoneBillRepository) {
        return (args) -> template.executeWithoutResult((status) -> {
            RatePlan ratePlan = new RegularRatePlan(Money.won(5), Duration.ofSeconds(10));
            em.persist(ratePlan);

            Contract contract = new Contract(
                                    new Phone("010-1111-2222"),
                                    ratePlan.getId(),
                                    LocalDate.of(2025, 1, 1),
                                    TimeInterval.of(LocalDate.of(2025, 1, 15), LocalDate.MAX));
            em.persist(contract);

            UUID [] sessions = { UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() };
            callRecordRepository.saveAllAndFlush(
                    List.of(
                        new CallRecord(UUID.randomUUID(), sessions[0], "010-1111-2222", "010-3333-4444", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 0)),
                        new CallRecord(UUID.randomUUID(), sessions[0], "010-1111-2222", "010-3333-4444", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 10)),
                        new CallRecord(UUID.randomUUID(), sessions[1], "010-1111-2222", "010-5555-6666", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 20)),
                        new CallRecord(UUID.randomUUID(), sessions[1], "010-1111-2222", "010-5555-6666", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 30)),
                        new CallRecord(UUID.randomUUID(), sessions[2], "010-1111-2222", "010-7777-8888", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 40)),
                        new CallRecord(UUID.randomUUID(), sessions[2], "010-1111-2222", "010-7777-8888", FAILED, NETWORK_FAILURE, LocalDateTime.of(2025, 1, 1, 10, 0, 50))
                ));

            settlementService.calculate(contract.getId());

            System.out.println(phoneBillRepository.findByContractId(contract.getId()).get().getFee());
        });
    }
}