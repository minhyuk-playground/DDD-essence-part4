package org.eternity.phone.contract.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.contract.domain.Call;
import org.eternity.phone.contract.domain.Contract;
import org.eternity.phone.contract.domain.PhoneBill;
import org.eternity.phone.contract.domain.RatePlan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PhoneBillService {
    private ContractRepository contractRepository;
    private RatePlanRepository ratePlanRepository;
    private CallTranslator callTranslator;
    private PhoneBillRepository phoneBillRepository;

    @Transactional
    public void calculate(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(IllegalArgumentException::new);
        RatePlan ratePlan = ratePlanRepository.findById(contract.getRatePlanId()).orElseThrow(IllegalArgumentException::new);

        Collection<Call> calls = callTranslator.translate(contract.getPhoneNumber());

        PhoneBill bill = new PhoneBill(contractId, contract.getPhone(), ratePlan.calculateFee(calls));

        phoneBillRepository.save(bill);
    }
}
