package com.dybek.chuckproducer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*
import org.springframework.context.ApplicationContext

@SpringBootTest(
    webEnvironment = RANDOM_PORT
)
class AppFunctionalTest {

    @Autowired
    private lateinit var context: ApplicationContext

    @Test
    fun `app starts successfully`() {
        assertThat(context).isNotNull
    }
}