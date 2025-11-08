package org.eternity.phone.charge.billing.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.charge.billing.domain.ContractPhone;
import org.eternity.phone.contract.service.ContractFacade;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContractPhoneTranslator {
    private ContractFacade contractFacade;

    public ContractPhone translate(Long contractId) {
        return contractFacade.getContract(contractId)
                .map(contract ->
                        new ContractPhone(contract.getId(), contract.getRatePlanId(), contract.getPhoneNumber()))
                .orElseThrow(IllegalArgumentException::new);
    }
}
