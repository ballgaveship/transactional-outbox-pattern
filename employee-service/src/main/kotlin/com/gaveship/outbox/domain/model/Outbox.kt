package com.gaveship.outbox.domain.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Entity
@DynamicUpdate
@SequenceGenerator(
    name = "outbox_seq_generator",
    sequenceName = "outbox_seq",
    initialValue = 1,
    allocationSize = 50
)
@TypeDef(typeClass = JsonType::class, defaultForType = JsonNode::class)
class Outbox(
    aggregateId: String,
    aggregateType: String,
    eventType: String,
    payload: JsonNode
) {
    constructor() : this(
        aggregateId = "",
        aggregateType = "",
        eventType = "",
        payload = jacksonObjectMapper().createObjectNode()
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_seq_generator")
    val id: Long? = null

    var aggregateId: String = aggregateId
        private set
    var aggregateType: String = aggregateType
        private set
    var eventType: String = eventType
        private set

    @Column(columnDefinition = "json", insertable = true, updatable = false)
    var payload: JsonNode = payload
        private set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Outbox

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Outbox(payload=$payload)"
    }
}