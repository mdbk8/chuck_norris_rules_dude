package com.dybek.chucktranslator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChuckTranslatorApplication

fun main(args: Array<String>) {
    runApplication<ChuckTranslatorApplication>(*args)
}