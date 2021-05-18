package com.dybek.chuckconsumer.infrastructure.rest

import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    value = [
        FeignAutoConfiguration::class,
        HttpMessageConvertersAutoConfiguration::class,
    ]
)
@EnableFeignClients(clients = [RestTranslatorClient::class])
@Configuration
class RestInfrastructureConfiguration
