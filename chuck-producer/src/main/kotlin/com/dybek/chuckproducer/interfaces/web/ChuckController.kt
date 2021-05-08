package com.dybek.chuckproducer.interfaces.web

import com.dybek.chuckproducer.domain.ChuckProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChuckController(
    private val chuckProducer: ChuckProducer
) {

    @PostMapping("/produce")
    fun produceChuck(): Unit = chuckProducer.produce()

}