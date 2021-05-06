package com.dybek.chuckproducer.infrastructure

import com.dybek.chuckproducer.infrastructure.kafka.ChuckPublisherConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    ChuckPublisherConfiguration::class
)
class InfrastructureConfiguration