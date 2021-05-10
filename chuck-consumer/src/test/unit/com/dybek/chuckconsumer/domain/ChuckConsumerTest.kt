package com.dybek.chuckconsumer.domain

import com.dybek.chuckconsumer.domain.model.Chuck
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ChuckConsumerTest {

    private val publisher = mock<ChuckPublisher>()
    private val translator = mock<Translator>()
    private val underTest: ChuckConsumer = ChuckConsumer(translator, publisher)

    @Test
    fun `translates fruit field of received Chuck instance`() {
        val chuck = Chuck(fruit = "orange")
        whenever(translator.translate(any())).thenReturn("")

        underTest.consume(chuck)

        verify(translator).translate(chuck.fruit)
    }

    @Test
    fun `published Chuck with translated fruit field`() {
        val chuck = Chuck(fruit = "orange")
        val translation = "${chuck.fruit}-translated"
        whenever(translator.translate(chuck.fruit)).thenReturn(translation)

        underTest.consume(chuck)

        verify(publisher).publish(chuck.copy(fruit = translation))
    }
}