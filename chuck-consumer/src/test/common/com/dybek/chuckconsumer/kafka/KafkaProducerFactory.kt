package com.dybek.chuckconsumer.kafka

import com.dybek.chuckconsumer.domain.model.Chuck
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.*

object KafkaProducerFactory {

    fun createTestProducer(
        topicName: String,
        bootstrapServers: String,
    ): KafkaTestProducer {
        val producerProperties = createProducerProperties(bootstrapServers)

        return KafkaTestProducer(
            KafkaProducer<String, Chuck>(
                producerProperties,
                StringSerializer(),
                JsonSerializer(ObjectMapper())
            ),
            topicName
        )
    }

    private fun createProducerProperties(bootstrapServers: String): Properties =
        Properties().apply {
            this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
            this[ProducerConfig.ACKS_CONFIG] = "all"
            this[ProducerConfig.RETRIES_CONFIG] = 0
        }
}
