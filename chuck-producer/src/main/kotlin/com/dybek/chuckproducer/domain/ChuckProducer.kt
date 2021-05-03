package com.dybek.chuckproducer.domain

class ChuckProducer(
    private val chuckGenerator: ChuckGenerator,
    private val chuckPublisher: ChuckPublisher
) {

    fun produce() {
        chuckPublisher.publish(chuckGenerator.generate())
    }
}
