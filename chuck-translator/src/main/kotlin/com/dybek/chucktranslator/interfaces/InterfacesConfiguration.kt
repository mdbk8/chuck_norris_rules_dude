package com.dybek.chucktranslator.interfaces

import com.dybek.chucktranslator.interfaces.web.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    WebConfiguration::class
)
@Configuration
class InterfacesConfiguration