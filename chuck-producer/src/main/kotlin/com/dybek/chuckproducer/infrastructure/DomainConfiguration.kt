package com.dybek.chuckproducer.infrastructure

import com.dybek.chuckproducer.domain.ChuckGenerator
import com.dybek.chuckproducer.domain.ChuckGeneratorImpl
import com.dybek.chuckproducer.domain.ChuckProducer
import com.dybek.chuckproducer.domain.ChuckPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun chuckProducer(chuckGenerator: ChuckGenerator, chuckPublisher: ChuckPublisher): ChuckProducer =
        ChuckProducer(chuckGenerator, chuckPublisher)

    @Bean
    fun chuckGenerator(): ChuckGenerator = ChuckGeneratorImpl()
}