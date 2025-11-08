package org.eternity.phone.charge.billing.domain;

public record ContractPhone(Long contractId, Long ratePlanId, Phone phone) {
    public String phopneNumber() {
        return phone.getNumber();
    }
}
