package com.dybek.chuckconsumer.domain

import org.mockito.kotlin.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class MockChuckConsumerTestConfig {

    @Bean
    fun chuckConsumerMock(): ChuckConsumer = mock()
}