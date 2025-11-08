package org.eternity.phone.charge.billing.domain;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import org.eternity.phone.shared.domain.ValueObject;

@Embeddable
@NoArgsConstructor
public class Phone extends ValueObject<Phone> {
    private String number;

    public Phone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
