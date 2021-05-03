package com.dybek.chuckproducer.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChuckGeneratorImplTest {

    @Test
    fun `generates Chuck instance`() {
        val generatedChuck = ChuckGeneratorImpl().generate()

        with(generatedChuck) {
            assertThat(this).isNotNull
            assertThat(fact).isNotBlank
            assertThat(beer).isNotBlank
            assertThat(fruit).isNotBlank
            assertThat(city).isNotBlank
            assertThat(country).isNotBlank
        }
    }
}