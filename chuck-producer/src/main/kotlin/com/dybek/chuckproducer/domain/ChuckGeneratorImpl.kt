package com.dybek.chuckproducer.domain

import com.github.javafaker.Faker

class ChuckGeneratorImpl : ChuckGenerator {
    override fun generate(): Chuck =
        with(Faker.instance()) {
            Chuck(
                id = code().isbnGs1(),
                fact = chuckNorris().fact(),
                beer = beer().style(),
                fruit = food().fruit(),
                city = address().city(),
                country = address().country()
            )
        }
}