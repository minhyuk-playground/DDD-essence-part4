package org.eternity.phone.tracking.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CallService {
    private CallRecordRepository callRecordRepository;

    @Transactional
    public void call(CallDTO callDTO) {
        callRecordRepository.save(callDTO.asCallRecord());
    }
}
