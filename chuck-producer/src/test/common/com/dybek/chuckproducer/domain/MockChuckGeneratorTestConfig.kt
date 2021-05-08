package com.dybek.chuckproducer.domain

import org.mockito.kotlin.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class MockChuckGeneratorTestConfig {

    @Bean
    @Primary
    fun mockChuckGenerator(): ChuckGenerator = mock()
}