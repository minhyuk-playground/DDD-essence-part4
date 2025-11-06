package org.eternity.phone.shared.domain;

public abstract class AggregateRoot<T extends DomainEntity<T, TID>, TID> extends DomainEntity<T, TID> {
}
