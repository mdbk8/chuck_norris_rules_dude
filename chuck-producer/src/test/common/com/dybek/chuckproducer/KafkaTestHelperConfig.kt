package com.dybek.chuckproducer

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class KafkaTestHelperConfig {

    @Bean
    fun consumerTestHelper(
        @Value("\${topic.name}") topicName: String,
        @Value("\${spring.embedded.kafka.brokers}") bootstrapServers: String
    ): KafkaConsumerTestHelper {
        return KafkaConsumerTestHelper(KafkaTestConsumerFactory.createTestConsumer(topicName, bootstrapServers))
    }

}