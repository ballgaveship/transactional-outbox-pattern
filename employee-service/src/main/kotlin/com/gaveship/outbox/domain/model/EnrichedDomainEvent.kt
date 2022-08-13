package com.gaveship.outbox.domain.model

import com.gaveship.events.DomainEvent

data class EnrichedDomainEvent<T : DomainEvent>(
    val aggregateType: String,
    val aggregateId: String,
    val domainEvent: T
) {
    val domainEventType: String
        get() = domainEvent.javaClass.simpleName
}