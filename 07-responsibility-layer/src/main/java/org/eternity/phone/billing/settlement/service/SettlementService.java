package org.eternity.phone.billing.settlement.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.billing.rateplan.domain.RatePlan;
import org.eternity.phone.billing.rateplan.service.RatePlanRepository;
import org.eternity.phone.billing.settlement.domain.*;
import org.eternity.phone.contract.service.ContractRepository;
import org.eternity.phone.shared.monetary.Money;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SettlementService {
    private ContractRepository contractRepository;
    private RatePlanRepository ratePlanRepository;
    private CallTranslator callTranslator;
    private ContractPhoneTranslator contractPhoneTranslator;
    private PhoneBillRepository phoneBillRepository;

    public void calculate(Long contractId) {
        ContractPhone contractPhone = contractPhoneTranslator.translate(contractId);
        RatePlan ratePlan = ratePlanRepository.findById(contractPhone.ratePlanId()).orElseThrow(IllegalArgumentException::new);

        Collection<Call> calls = callTranslator.translate(contractPhone.phopneNumber());
        Money fee = ratePlan.calculateFee(calls);

        PhoneBill bill = new PhoneBill(contractId, new Phone(), fee);

        phoneBillRepository.save(bill);
    }
}
