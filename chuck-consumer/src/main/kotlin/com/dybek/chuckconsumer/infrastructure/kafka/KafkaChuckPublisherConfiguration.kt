package com.dybek.chuckconsumer.infrastructure.kafka

import com.dybek.chuckconsumer.domain.ChuckPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaChuckPublisherConfiguration {

    @Bean
    fun publisher(): ChuckPublisher = object: ChuckPublisher {}
}