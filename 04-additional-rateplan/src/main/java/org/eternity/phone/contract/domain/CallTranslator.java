package org.eternity.phone.contract.domain;

import java.util.Collection;

public interface CallTranslator {
    Collection<Call> translate(String phoneNumber);
}
