package com.gaveship.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.gaveship.events.DomainEvent
import io.awspring.cloud.messaging.config.annotation.NotificationMessage
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.converter.MappingJackson2MessageConverter

@SpringBootApplication
class ConsumerApplication {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @Bean
    fun mappingJackson2MessageConverter(objectMapper: ObjectMapper): MappingJackson2MessageConverter {
        val jackson2MessageConverter = MappingJackson2MessageConverter()
        jackson2MessageConverter.objectMapper = objectMapper
        return jackson2MessageConverter
    }

    @SqsListener(value = ["\${input_queue}"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun receiveQueueMessages(@NotificationMessage domainEvent: DomainEvent) {
        log.info {"Received domain event $domainEvent" }
    }
}

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}
