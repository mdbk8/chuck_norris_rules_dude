package com.dybek.chuckproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChuckProducerApplication

fun main(args: Array<String>) {
    runApplication<ChuckProducerApplication>(*args)
}
