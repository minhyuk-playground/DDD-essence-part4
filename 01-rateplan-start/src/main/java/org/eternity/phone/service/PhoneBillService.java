package org.eternity.phone.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Collection;

@Service
@AllArgsConstructor
public class PhoneBillService {
    private ContractRepository contractRepository;
    private RatePlanRepository ratePlanRepository;
    private CallRecordRepository callRecordsRepository;
    private PhoneBillRepository phoneBillRepository;

    @Transactional
    public void calculate(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(IllegalArgumentException::new);
        RatePlan ratePlan = ratePlanRepository.findById(contract.getRatePlanId()).orElseThrow(IllegalArgumentException::new);

        Collection<CallRecord[]> callRecords = callRecordsRepository.findCallRecordsToBill(contract.getPhoneNumber());

        PhoneBill bill = new PhoneBill(contractId, contract.getPhone(), ratePlan.calculateFee(callRecords));

        phoneBillRepository.save(bill);
    }
}
