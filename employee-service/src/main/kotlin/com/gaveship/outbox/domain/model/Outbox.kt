package com.gaveship.outbox.domain.model

import com.fasterxml.jackson.databind.JsonNode
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
class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_seq_generator")
    var id: Long? = null
    var aggregateType: String? = null
    var aggregateId: String? = null
    var eventType: String? = null

    @Column(columnDefinition = "json", insertable = true, updatable = false)
    var payload: JsonNode? = null
}