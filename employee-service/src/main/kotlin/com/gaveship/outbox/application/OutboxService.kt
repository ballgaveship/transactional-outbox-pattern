package com.gaveship.outbox.application

import com.gaveship.outbox.domain.model.EnrichedDomainEvent

interface OutboxService {
    fun handleEnrichedDomainEvent(event: EnrichedDomainEvent<*>)
}