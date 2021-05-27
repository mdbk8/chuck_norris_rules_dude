package com.dybek.chuckconsumer.infrastructure.kafka

import com.dybek.chuckconsumer.domain.ChuckPublisher
import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@ImportAutoConfiguration(
    KafkaAutoConfiguration::class
)
class KafkaChuckPublisherConfiguration {

    @Bean
    fun publisher(
        @Value("\${output.topic.name}") topicName: String,
        producerTemplate: KafkaTemplate<String, Chuck>
    ): ChuckPublisher = KafkaChuckPublisher(topicName, producerTemplate)

    @Bean
    fun producerTemplate(producerFactory: ProducerFactory<String, Chuck>): KafkaTemplate<String, Chuck> =
        KafkaTemplate(producerFactory)

    @Bean
    fun producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, Chuck> =
        kafkaProperties.buildProducerProperties()
            .apply {
                putAll(
                    mapOf(
                        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
                    )
                )
            }.let { DefaultKafkaProducerFactory(it) }
}