package com.dybek.chuckconsumer.interfaces.kafka

import com.dybek.chuckconsumer.domain.ChuckConsumer
import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener

class KafkaChuckConsumer(private val chuckConsumer: ChuckConsumer) {

    @KafkaListener(
        topics = ["\${input.topic.name}"],
        containerFactory = "chuckKafkaListenerFactory"
    )
    fun consumeChuck(record: ConsumerRecord<String, Chuck>) {
        chuckConsumer.consume(record.value())
    }
}
