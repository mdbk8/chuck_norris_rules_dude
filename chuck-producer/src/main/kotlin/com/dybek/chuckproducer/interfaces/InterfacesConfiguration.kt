package com.dybek.chuckproducer.interfaces

import com.dybek.chuckproducer.interfaces.web.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    WebConfiguration::class
)
@Configuration
class InterfacesConfiguration