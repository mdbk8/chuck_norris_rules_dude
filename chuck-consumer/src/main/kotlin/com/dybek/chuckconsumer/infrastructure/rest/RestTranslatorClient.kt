package com.dybek.chuckconsumer.infrastructure.rest

import com.dybek.chuckconsumer.domain.Translator
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "chuck-translator",
    url = "\${chuck.translator.url}"
)
interface RestTranslatorClient : Translator {

    @GetMapping(path = ["/translate/{word}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun translate(@PathVariable("word") wordToTranslate: String): Map<String, String>
}