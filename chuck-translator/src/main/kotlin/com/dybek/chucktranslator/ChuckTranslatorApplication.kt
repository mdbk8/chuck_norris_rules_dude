package com.dybek.chucktranslator

import com.dybek.chucktranslator.domain.DomainConfiguration
import com.dybek.chucktranslator.infrastructure.InfrastructureConfiguration
import com.dybek.chucktranslator.interfaces.InterfacesConfiguration
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(
    DomainConfiguration::class,
    InfrastructureConfiguration::class,
    InterfacesConfiguration::class
)
@SpringBootConfiguration
class ChuckTranslatorApplication

fun main(args: Array<String>) {
    runApplication<ChuckTranslatorApplication>(*args)
}