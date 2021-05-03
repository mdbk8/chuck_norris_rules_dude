package com.dybek.chuckproducer.infrastructure.kafka

import com.dybek.chuckproducer.domain.Chuck
import com.dybek.chuckproducer.domain.ChuckPublisher

class KafkaChuckPublisher : ChuckPublisher {
    override fun publish(chuck: Chuck) {
    }
}