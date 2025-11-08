package org.eternity.phone.charge.billing.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.charge.billing.domain.ContractPhone;
import org.eternity.phone.charge.billing.domain.ContractPhoneTranslator;
import org.eternity.phone.charge.billing.domain.Phone;
import org.eternity.phone.contract.service.ContractRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContractToContractPhoneTranslator implements ContractPhoneTranslator {
    private ContractRepository contractRepository;

    public ContractPhone translate(Long contractId) {
        return contractRepository.findById(contractId)
                    .map(contract ->
                            new ContractPhone(contract.getId(), contract.getRatePlanId(), new Phone(contract.getPhoneNumber())))
                    .orElseThrow(IllegalArgumentException::new);
    }
}
