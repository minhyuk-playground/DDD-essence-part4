package org.eternity.phone.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.domain.CallRecord;
import org.eternity.phone.domain.Contract;
import org.eternity.phone.domain.PhoneBill;
import org.eternity.phone.domain.RatePlan;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SettlementService {
    private ContractRepository contractRepository;
    private RatePlanRepository ratePlanRepository;
    private CallRecordsBillingRepository callRecordsBillingRepository;
    private PhoneBillRepository phoneBillRepository;

    public void calculate(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(IllegalArgumentException::new);
        RatePlan ratePlan = ratePlanRepository.findById(contract.getRatePlanId()).orElseThrow(IllegalArgumentException::new);

        Collection<CallRecord[]> callRecords = callRecordsBillingRepository.findCallRecordsToBill(contract.getPhoneNumber());

        PhoneBill bill = new PhoneBill(contractId, contract.getPhone(), ratePlan.calculateFee(callRecords));

        phoneBillRepository.save(bill);
    }
}
