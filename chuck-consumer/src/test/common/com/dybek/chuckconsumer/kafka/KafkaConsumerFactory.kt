package com.dybek.chuckconsumer.kafka

import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.time.Duration
import java.util.*

object KafkaConsumerFactory {

    fun createTestConsumer(
        topicName: String,
        bootstrapServers: String,
        defaultPoolDuration: Duration = Duration.ofSeconds(2)
    ): KafkaTestConsumer {

        val consumerProperties = createConsumerProperties(bootstrapServers)

        return KafkaTestConsumer(
            consumer = KafkaConsumer<String, Chuck>(
                consumerProperties,
                StringDeserializer(),
                JsonDeserializer(Chuck::class.java, false)
            ),
            topic = topicName,
            defaultPoolDuration = defaultPoolDuration
        )
    }

    private fun createConsumerProperties(bootstrapServers: String): Properties {
        return Properties().apply {
            this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
            this[ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 60000
            this[ConsumerConfig.GROUP_ID_CONFIG] = "TestGroupId"
            this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = "false"
            this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = OffsetResetStrategy.EARLIEST.name.toLowerCase()
        }
    }

}
