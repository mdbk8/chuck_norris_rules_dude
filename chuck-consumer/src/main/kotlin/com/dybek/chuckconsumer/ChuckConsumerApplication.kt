package com.dybek.chuckconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChuckConsumerApplication

fun main(args: Array<String>) {
    runApplication<ChuckConsumerApplication>(*args)
}