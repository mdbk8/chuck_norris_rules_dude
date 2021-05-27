package com.dybek.chuckconsumer.kafka

import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class KafkaTestProducer(
    private val kafkaProducer: KafkaProducer<String, Chuck>,
    private val topicName: String
) {

    fun sendAsync(key: String, value: Chuck) {
        kafkaProducer.send(ProducerRecord(topicName, key, value))
    }

    fun send(key: String, value: Chuck) {
        kafkaProducer.send(ProducerRecord(topicName, key, value)).get()
    }

    fun close() = kafkaProducer.close()
}
