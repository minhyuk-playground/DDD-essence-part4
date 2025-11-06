package org.eternity.phone.billing.settlement.domain;

import java.util.Collection;

public interface CallTranslator {
    Collection<Call> translate(String phoneNumber);
}
