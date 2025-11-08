package org.eternity.phone.contract.billing.domain;

import java.util.Collection;

public interface CallTranslator {
    Collection<Call> translate(String phoneNumber);
}
