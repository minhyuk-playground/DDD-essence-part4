package org.eternity.phone.charge.billing.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.shared.temporal.TimeInterval;
import org.eternity.phone.charge.billing.domain.Call;
import org.eternity.phone.tracking.service.CallRecordFacade;
import org.eternity.phone.tracking.service.CallRecordPair;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class CallTranslator {
    private CallRecordFacade callRecordFacade;

    public Collection<Call> translate(String phoneNumber) {
        Collection<CallRecordPair> pairs = callRecordFacade.getCallRecords(phoneNumber);
        return pairs.stream()
                .map(pair -> new Call(
                                pair.from().getCallingNumber(),
                                TimeInterval.of(pair.from().getOccurredTime(), pair.to().getOccurredTime())))
                .toList();
    }
}
