package org.eternity.phone.charge.billing.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.charge.billing.domain.Call;
import org.eternity.phone.charge.billing.domain.ContractPhone;
import org.eternity.phone.contract.domain.Contract;
import org.eternity.phone.charge.billing.domain.PhoneBill;
import org.eternity.phone.charge.rateplan.domain.RatePlan;
import org.eternity.phone.contract.service.ContractRepository;
import org.eternity.phone.charge.rateplan.service.RatePlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PhoneBillService {
    private RatePlanRepository ratePlanRepository;
    private PhoneBillRepository phoneBillRepository;
    private CallTranslator callTranslator;
    private ContractPhoneTranslator contractPhoneTranslator;

    @Transactional
    public void calculate(Long contractId) {
        ContractPhone contract = contractPhoneTranslator.translate(contractId);
        RatePlan ratePlan = ratePlanRepository.findById(contract.ratePlanId()).orElseThrow(IllegalArgumentException::new);

        Collection<Call> calls = callTranslator.translate(contract.phoneNumber());

        PhoneBill bill = new PhoneBill(contractId, contract.getPhone(), ratePlan.calculateFee(calls));

        phoneBillRepository.save(bill);
    }
}
