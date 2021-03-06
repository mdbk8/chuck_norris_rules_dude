package com.dybek.chuckconsumer.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

@TestConfiguration
class KafkaTestHelperConfig {

    @Bean(destroyMethod = "close")
    @Lazy
    fun kafkaConsumerTestHelper(
        @Value("\${input.topic.name}") topicName: String,
        @Value("\${spring.embedded.kafka.brokers}") bootstrapServers: String
    ): KafkaProducerTestHelper =
        KafkaProducerTestHelper(
            KafkaProducerFactory.createTestProducer(topicName, bootstrapServers),
            KafkaConsumerFactory.createTestConsumer(topicName, bootstrapServers)
        )


    @Bean(destroyMethod = "close")
    @Lazy
    fun kafkaProducerTestHelper(
        @Value("\${output.topic.name}") topicName: String,
        @Value("\${spring.embedded.kafka.brokers}") bootstrapServers: String
    ): KafkaProducerTestHelper =
        KafkaProducerTestHelper(
            KafkaProducerFactory.createTestProducer(topicName, bootstrapServers),
            KafkaConsumerFactory.createTestConsumer(topicName, bootstrapServers)
        )
}
