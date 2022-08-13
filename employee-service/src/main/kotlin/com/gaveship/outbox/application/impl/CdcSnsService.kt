package com.gaveship.outbox.application.impl

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.MessageAttributeValue
import com.amazonaws.services.sns.model.PublishRequest
import com.gaveship.outbox.application.CdcService
import com.gaveship.outbox.domain.OutboxRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CdcSnsService(
    private val outboxRepository: OutboxRepository,
    private val amazonSNS: AmazonSNS
) : CdcService {
    companion object {
        private val log = KotlinLogging.logger { }

        private const val EVENT_TYPE = "eventType"
        private const val STRING = "String"
    }

    @Value("\${cdc.sns_topic}")
    private val snsTopic: String? = null
    @Value("\${cdc.batch_size}")
    private val batchSize: Int = 0

    @Transactional
    @Scheduled(fixedDelayString = "\${cdc.polling_ms}")
    override fun forwardEvent() {
        val entities = outboxRepository.findAllByOrderByIdAsc(Pageable.ofSize(batchSize)).toList()
        entities.forEach {
            amazonSNS.publish(
                PublishRequest()
                    .withTopicArn(snsTopic)
                    .withMessage(it.payload.toString())
                    .withMessageGroupId("${it.aggregateType} - ${it.aggregateId}")
                    .withMessageAttributes(
                        mapOf(
                            EVENT_TYPE to MessageAttributeValue()
                                .withDataType(STRING)
                                .withStringValue(it.eventType)
                        )
                    )
            )
        }
        log.info { "send sns. size=${entities.size}" }
        outboxRepository.deleteAllInBatch(entities)
    }
}