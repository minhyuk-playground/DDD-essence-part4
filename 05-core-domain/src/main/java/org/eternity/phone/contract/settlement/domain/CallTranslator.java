package org.eternity.phone.contract.settlement.domain;

import java.util.Collection;

public interface CallTranslator {
    Collection<Call> translate(String phoneNumber);
}
