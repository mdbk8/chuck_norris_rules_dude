package com.dybek.chuckconsumer.domain

import com.dybek.chuckconsumer.domain.model.Chuck

class ChuckConsumer(private val translator: Translator, private val publisher: ChuckPublisher) {

    fun consume(chuck: Chuck) {
        val translatedFruit = translator.translate(chuck.fruit)

        publisher.publish(chuck.copy(fruit = translatedFruit))
    }
}
