package com.gaveship.outbox.application.impl

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.gaveship.outbox.application.OutboxService
import com.gaveship.outbox.domain.model.EnrichedDomainEvent
import com.gaveship.outbox.domain.model.Outbox
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class OutboxServiceImpl(
    private val em: EntityManager,
    private val objectMapper: ObjectMapper
) : OutboxService {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @EventListener
    override fun handleEnrichedDomainEvent(event: EnrichedDomainEvent<*>) {
        em.persist(
            Outbox(
                event.aggregateId,
                event.aggregateType,
                event.domainEventType,
                objectMapper.convertValue(event.domainEvent, JsonNode::class.java)
            )
        )
        log.debug { "event handled [$event]" }
    }
}