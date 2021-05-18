package com.dybek.chuckconsumer.infrastructure.rest

import com.dybek.chuckconsumer.domain.Translator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MockedRestInfrastructureConfiguration {

    @Bean
    fun mockTranslator(): Translator =
        object : Translator {
            override fun translate(wordToTranslate: String): Map<String, String> =
                mapOf(wordToTranslate to "$wordToTranslate-translated")
        }
}