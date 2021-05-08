package com.dybek.chuckproducer.domain

import org.mockito.kotlin.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class MockChuckProducerTestConfig {

    @Bean
    fun mockChuckProducer(): ChuckProducer = mock()
}