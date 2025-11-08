package org.eternity.phone.charge.billing.domain;

import org.eternity.phone.contract.domain.Phone;

public record ContractPhone(Long contractId, Long ratePlanId, String phoneNumber) {
    public Phone getPhone() {
        return new Phone(phoneNumber);
    }
}
