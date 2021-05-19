package com.dybek.chuckconsumer.infrastructure

import com.dybek.chuckconsumer.domain.ChuckConsumer
import com.dybek.chuckconsumer.domain.ChuckPublisher
import com.dybek.chuckconsumer.domain.Translator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun chuckConsumer(translator: Translator, publisher: ChuckPublisher): ChuckConsumer =
        ChuckConsumer(translator, publisher)
}