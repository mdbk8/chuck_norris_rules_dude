package com.dybek.chuckconsumer.interfaces

import com.dybek.chuckconsumer.interfaces.web.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    WebConfiguration::class
)
@Configuration
class InterfacesConfiguration