package org.eternity.phone.billing.settlement.domain;

public record ContractPhone(Long contractId, Long ratePlanId, Phone phone) {
    public String phopneNumber() {
        return phone.getNumber();
    }
}
