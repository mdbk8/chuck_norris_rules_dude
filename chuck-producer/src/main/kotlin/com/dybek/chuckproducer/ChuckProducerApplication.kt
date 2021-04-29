package com.dybek.chuckproducer

import com.dybek.chuckproducer.domain.DomainConfiguration
import com.dybek.chuckproducer.infrastructure.InfrastructureConfiguration
import com.dybek.chuckproducer.interfaces.InterfacesConfiguration
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(
    DomainConfiguration::class,
    InterfacesConfiguration::class,
    InfrastructureConfiguration::class
)
@SpringBootConfiguration
class ChuckProducerApplication

fun main(args: Array<String>) {
    runApplication<ChuckProducerApplication>(*args)
}
