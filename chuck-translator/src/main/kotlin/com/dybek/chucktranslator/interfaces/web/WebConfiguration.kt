package com.dybek.chucktranslator.interfaces.web

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Import(
    DispatcherServletAutoConfiguration::class,
    ServletWebServerFactoryAutoConfiguration::class
)
@EnableWebMvc
@Configuration
class WebConfiguration