package org.eternity.phone.charge.billing.domain;

import java.util.Collection;

public interface CallTranslator {
    Collection<Call> translate(String phoneNumber);
}
