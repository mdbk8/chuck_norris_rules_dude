package com.dybek.chuckproducer.domain

import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class ChuckProducerTest {

    @Test
    fun `publishes Chuck instance`() {
        val chuckPublisher = mock<ChuckPublisher>()
        val chuckGenerator = mock<ChuckGenerator>()
        val generatedChuck = Chuck(
            fact = "fact",
            beer = "beer",
            fruit = "fruit",
            city = "city",
            country = "country"
        )
        given(chuckGenerator.generate()).willReturn(generatedChuck)

        val underTest = ChuckProducer(chuckGenerator, chuckPublisher)

        underTest.produce()

        verify(chuckPublisher, times(1)).publish(generatedChuck)
    }
}