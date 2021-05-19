package com.dybek.chuckconsumer.domain

import org.mockito.kotlin.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class MockedChuckPublisherTestConfig {

    @Bean
    @Primary
    fun mockChuckPublisher(): ChuckPublisher = mock()
}