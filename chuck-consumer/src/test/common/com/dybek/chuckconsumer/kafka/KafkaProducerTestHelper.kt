package com.dybek.chuckconsumer.kafka

import com.dybek.chuckconsumer.domain.model.Chuck
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import java.time.Duration

class KafkaProducerTestHelper(
    private val producer: KafkaTestProducer,
    private val consumer: KafkaTestConsumer
) {

    fun sendMessageAndWaitToAppear(key: String, value: Chuck) {
        producer.send(key, value)
        waitForMessage(key, value)
    }

    private fun waitForMessage(key: String, value: Chuck) =
        await().atMost(Duration.ofSeconds(30))
            .untilAsserted {
                val readMessages = consumer.consumeAllFromTheBeginning()
                assertThat(readMessages).contains(KafkaMessage(key, value))
            }
}