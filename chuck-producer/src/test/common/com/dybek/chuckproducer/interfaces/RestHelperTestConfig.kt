package com.dybek.chuckproducer.interfaces

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

@TestConfiguration
class RestHelperTestConfig {

    @Bean
    @Lazy
    fun restRequestSender(
        @Value("\${local.server.port}") port: Int,
        @Value("\${server.servlet.context-path}") contextPath: String
    ): RestRequestSender = RestRequestSender(port, contextPath)
}