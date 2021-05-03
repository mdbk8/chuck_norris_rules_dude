package com.dybek.chuckproducer.infrastructure

import com.dybek.chuckproducer.domain.ChuckGeneratorImpl
import com.dybek.chuckproducer.domain.ChuckProducer
import com.dybek.chuckproducer.domain.ChuckPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun chuckProducer(chuckPublisher: ChuckPublisher): ChuckProducer =
        ChuckProducer(ChuckGeneratorImpl(), chuckPublisher)
}