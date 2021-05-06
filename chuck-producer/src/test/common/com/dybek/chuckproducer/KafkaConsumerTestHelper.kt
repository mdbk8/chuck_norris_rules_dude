package com.dybek.chuckproducer

import com.dybek.chuckproducer.domain.Chuck
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import java.time.Duration

class KafkaConsumerTestHelper(
    private val kafkaTestConsumer: KafkaTestConsumer
) {

    fun consumeExpectedNumberOfMessagesFilteredByKey(
        key: String,
        expectedNumberOfMessages: Int
    ): List<KafkaMessage<Chuck>> {
        val actualMessages: MutableList<KafkaMessage<Chuck>> = ArrayList()

        await().atMost(Duration.ofSeconds(30)).untilAsserted {
            val messages = kafkaTestConsumer.consumeAllMessagesFromTheBeginning()
            actualMessages.addAll(messages)

            assertThat(actualMessages.filter { it.key == key }.size).isEqualTo(expectedNumberOfMessages)
        }

        return actualMessages.filter { it.key == key }
    }
}
