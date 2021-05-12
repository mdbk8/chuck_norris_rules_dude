package com.dybek.chuckconsumer.interfaces.kafka

import com.dybek.chuckconsumer.domain.ChuckConsumer
import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@ImportAutoConfiguration(
    KafkaAutoConfiguration::class
)
@EnableKafka
class KafkaChuckConsumerConfiguration {

    @Bean
    fun kafkaChuckConsumer(chuckConsumer: ChuckConsumer): KafkaChuckConsumer {
        return KafkaChuckConsumer(chuckConsumer)
    }

    @Bean
    fun chuckKafkaListenerFactory(consumerFactory: ConsumerFactory<String, Chuck>):
            ConcurrentKafkaListenerContainerFactory<String, Chuck> =
        ConcurrentKafkaListenerContainerFactory<String, Chuck>()
            .apply {
                setConsumerFactory(consumerFactory)
            }

    @Bean
    fun consumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, Chuck> =
        DefaultKafkaConsumerFactory(
            kafkaProperties.buildConsumerProperties(),
            StringDeserializer(),
            JsonDeserializer(Chuck::class.java, false)
        )
}
