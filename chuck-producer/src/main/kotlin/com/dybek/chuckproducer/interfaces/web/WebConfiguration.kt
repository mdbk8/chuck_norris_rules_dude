package com.dybek.chuckproducer.interfaces.web

import com.dybek.chuckproducer.domain.ChuckProducer
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Import(
    DispatcherServletAutoConfiguration::class,
    ServletWebServerFactoryAutoConfiguration::class,
    EmbeddedWebServerFactoryCustomizerAutoConfiguration::class,
    PropertyPlaceholderAutoConfiguration::class
)
@EnableWebMvc
@Configuration
class WebConfiguration {

    @Bean
    fun chuckController(chuckProducer: ChuckProducer): ChuckController = ChuckController(chuckProducer)
}