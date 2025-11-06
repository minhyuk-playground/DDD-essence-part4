package org.eternity.phone.shared.monetary;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Money implements Comparable<Money> {
    public static final Money ZERO = Money.won(0);

    private BigDecimal amount;

    public static Money won(long amount) {
        return won(BigDecimal.valueOf(amount));
    }

    public static Money won(BigDecimal amount) {
        return new Money(amount);
    }

    public Money plus(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money minus(Money other) {
        return new Money(amount.subtract(other.amount));
    }

    public Money divide(double divisor) {
        return new Money(amount.divide(BigDecimal.valueOf(divisor), RoundingMode.HALF_EVEN));
    }

    public Money times(double times) {
        return new Money(amount.multiply(BigDecimal.valueOf(times)).setScale(0, RoundingMode.HALF_EVEN));
    }

    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return compareTo(money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}