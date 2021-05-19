package com.dybek.chuckconsumer.infrastructure

import com.dybek.chuckconsumer.infrastructure.kafka.KafkaChuckPublisherConfiguration
import com.dybek.chuckconsumer.infrastructure.rest.MockedRestInfrastructureConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    value = [
        MockedRestInfrastructureConfiguration::class,
        KafkaChuckPublisherConfiguration::class
    ]
)
@Configuration
class InfrastructureConfiguration