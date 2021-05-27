package com.dybek.chuckconsumer.infrastructure.kafka

import com.dybek.chuckconsumer.domain.ChuckPublisher
import com.dybek.chuckconsumer.domain.model.Chuck
import org.springframework.kafka.core.KafkaTemplate

class KafkaChuckPublisher(
    private val topicName: String,
    private val producerTemplate: KafkaTemplate<String, Chuck>
) : ChuckPublisher {

    override fun publish(chuck: Chuck) {
        producerTemplate.send(topicName, chuck.id, chuck)
    }
}