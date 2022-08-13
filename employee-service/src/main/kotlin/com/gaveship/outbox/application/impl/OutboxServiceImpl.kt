package com.gaveship.outbox.application.impl

import com.gaveship.outbox.application.OutboxService
import com.gaveship.outbox.domain.model.EnrichedDomainEvent
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class OutboxServiceImpl : OutboxService {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @EventListener
    override fun handleEnrichedDomainEvent(event: EnrichedDomainEvent<*>) {
        log.info { "listen event [$event]" }
    }
}