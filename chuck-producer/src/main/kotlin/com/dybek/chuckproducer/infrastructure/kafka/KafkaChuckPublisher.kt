package com.dybek.chuckproducer.infrastructure.kafka

import com.dybek.chuckproducer.domain.Chuck
import com.dybek.chuckproducer.domain.ChuckPublisher
import org.springframework.kafka.core.KafkaTemplate

class KafkaChuckPublisher(
    private val topicName: String,
    private val producerTemplate: KafkaTemplate<String, Chuck>
) : ChuckPublisher {

    override fun publish(chuck: Chuck) {
        producerTemplate.send(topicName, chuck.id, chuck)
    }
}