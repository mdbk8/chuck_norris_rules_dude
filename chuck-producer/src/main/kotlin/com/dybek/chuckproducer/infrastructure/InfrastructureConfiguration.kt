package com.dybek.chuckproducer.infrastructure

import com.dybek.chuckproducer.domain.ChuckPublisher
import com.dybek.chuckproducer.infrastructure.kafka.KafkaChuckPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfrastructureConfiguration {

    @Bean
    fun chuckPublisher(): ChuckPublisher = KafkaChuckPublisher()
}