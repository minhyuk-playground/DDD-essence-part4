package org.eternity.phone.tracking.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.tracking.domain.CallRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Component
@AllArgsConstructor
public class CallRecordFacade {
    private CallRecordRepository callRecordRepository;

    public Collection<CallRecordPair> getCallRecords(String phoneNumber) {
        Collection<CallRecord[]> result = callRecordRepository.findCallRecordsToBill(phoneNumber);
        return result.stream().map(call -> new CallRecordPair(call[0], call[1])).toList();
    }
}
