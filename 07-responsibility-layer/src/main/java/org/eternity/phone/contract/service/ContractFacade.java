package org.eternity.phone.contract.service;

import lombok.AllArgsConstructor;
import org.eternity.phone.contract.domain.Contract;
import org.springframework.stereotype.Component;

import java.nio.channels.FileChannel;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ContractFacade {
    private ContractRepository contractRepository;

    public Optional<Contract> getContract(Long contractId) {
        return contractRepository.findById(contractId);
    }
}
