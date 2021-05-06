package com.dybek.chuckproducer.infrastructure.kafka

import com.dybek.chuckproducer.domain.Chuck
import org.apache.kafka.clients.admin.NewTopic
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
class ChuckPublisherConfiguration {

    @Bean
    fun chuckPublisher(
        @Value("\${topic.name}") topicName: String,
        producerTemplate: KafkaTemplate<String, Chuck>
    ): KafkaChuckPublisher =
        KafkaChuckPublisher(topicName, producerTemplate)

    @Bean
    fun producerTemplate(producerFactory: ProducerFactory<String, Chuck>): KafkaTemplate<String, Chuck> =
        KafkaTemplate(producerFactory)

    @Bean
    fun producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, Chuck> {

        val producerConfigs = kafkaProperties.buildProducerProperties()
            .apply {
                putAll(
                    mapOf(
                        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
                    )
                )
            }

        return DefaultKafkaProducerFactory(producerConfigs)
    }

    @Bean
    fun topic(
        @Value("\${topic.name}") topicName: String,
        @Value("\${topic.numOfPartitions}") numOfPartitions: Int,
        @Value("\${topic.replicationFactor}") replicationFactor: Short
    ): NewTopic = NewTopic(topicName, numOfPartitions, replicationFactor)
}