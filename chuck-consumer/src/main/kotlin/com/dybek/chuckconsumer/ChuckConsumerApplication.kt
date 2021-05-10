package com.dybek.chuckconsumer

import com.dybek.chuckconsumer.infrastructure.DomainConfiguration
import com.dybek.chuckconsumer.infrastructure.InfrastructureConfiguration
import com.dybek.chuckconsumer.interfaces.InterfacesConfiguration
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(
    DomainConfiguration::class,
    InfrastructureConfiguration::class,
    InterfacesConfiguration::class
)
@SpringBootConfiguration
class ChuckConsumerApplication

fun main(args: Array<String>) {
    runApplication<ChuckConsumerApplication>(*args)
}