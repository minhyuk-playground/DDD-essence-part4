package org.eternity.phone;

import jakarta.persistence.EntityManager;
import org.eternity.phone.contract.domain.Contract;
import org.eternity.phone.contract.domain.Phone;
import org.eternity.phone.charge.rateplan.domain.DiscountableRatePlan;
import org.eternity.phone.charge.rateplan.domain.RatePlan;
import org.eternity.phone.charge.rateplan.domain.RegularRatePlan;
import org.eternity.phone.charge.rateplan.domain.TaxableRatePlan;
import org.eternity.phone.tracking.domain.CallRecord;
import org.eternity.phone.tracking.service.CallRecordRepository;
import org.eternity.phone.charge.billing.service.PhoneBillRepository;
import org.eternity.phone.charge.billing.service.PhoneBillService;
import org.eternity.phone.shared.monetary.Money;
import org.eternity.phone.shared.temporal.TimeInterval;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.eternity.phone.tracking.domain.CallPhase.*;
import static org.eternity.phone.tracking.domain.CallStatus.*;

@Configuration
public class RunnerConfig {
    @Bean
    ApplicationRunner runner(
            EntityManager em,
            TransactionTemplate template,
            CallRecordRepository callRecordRepository,
            PhoneBillService phoneBillService,
            PhoneBillRepository phoneBillRepository) {

        return (args) ->
                template.executeWithoutResult(
                        (status) -> {
                            RatePlan ratePlan = new RegularRatePlan(
                                                        Money.won(5),
                                                        Duration.ofSeconds(10),
                                                        new TaxableRatePlan(0.1,
                                                                new DiscountableRatePlan(Money.won(1))));
                            em.persist(ratePlan);

                            Contract contract = new Contract(
                                    new Phone("010-1111-2222"),
                                    ratePlan.getId(),
                                    LocalDate.of(2025, 1, 1),
                                    TimeInterval.of(LocalDate.of(2025, 1, 15), LocalDate.MAX));
                            em.persist(contract);

                            UUID[] sessions = {UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()};
                            callRecordRepository.saveAllAndFlush(
                                    List.of(
                                            new CallRecord(UUID.randomUUID(), sessions[0], "010-1111-2222", "010-3333-4444", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 0)),
                                            new CallRecord(UUID.randomUUID(), sessions[0], "010-1111-2222", "010-3333-4444", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 10)),
                                            new CallRecord(UUID.randomUUID(), sessions[1], "010-1111-2222", "010-5555-6666", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 20)),
                                            new CallRecord(UUID.randomUUID(), sessions[1], "010-1111-2222", "010-5555-6666", COMPLETED, NORMAL_CLEARING, LocalDateTime.of(2025, 1, 1, 10, 0, 30)),
                                            new CallRecord(UUID.randomUUID(), sessions[2], "010-1111-2222", "010-7777-8888", STARTED, IN_PROGRESS, LocalDateTime.of(2025, 1, 1, 10, 0, 40)),
                                            new CallRecord(UUID.randomUUID(), sessions[2], "010-1111-2222", "010-7777-8888", FAILED, NETWORK_FAILURE, LocalDateTime.of(2025, 1, 1, 10, 0, 50))
                                    ));

                            phoneBillService.calculate(contract.getId());

                            System.out.println(phoneBillRepository.findByContractId(contract.getId()).get().getFee());
                        });
    }
}
