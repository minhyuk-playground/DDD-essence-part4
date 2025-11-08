package org.eternity.phone.contract.billing.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.contract.billing.domain.Call;
import org.eternity.phone.contract.billing.domain.CallTranslator;
import org.eternity.phone.shared.temporal.TimeInterval;
import org.eternity.phone.tracking.domain.CallRecord;
import org.eternity.phone.tracking.service.CallRecordRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CallRecordToCallTranslator implements CallTranslator {
    private CallRecordRepository callRecordRepository;

    @Override
    public Collection<Call> translate(String phoneNumber) {
        Collection<CallRecord[]> callRecords = callRecordRepository.findCallRecordsToBill(phoneNumber);
        return callRecords.stream()
                .map(pair ->
                        new Call(
                            pair[0].getCallingNumber(),
                            TimeInterval.of(pair[0].getOccurredTime(), pair[1].getOccurredTime())))
                .collect(Collectors.toUnmodifiableSet());
    }
}
