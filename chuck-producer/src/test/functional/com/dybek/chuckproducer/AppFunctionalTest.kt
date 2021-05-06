package com.dybek.chuckproducer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*
import org.springframework.context.ApplicationContext
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    properties = [
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ]
)
@EmbeddedKafka(
    topics = ["\${topic.name}"],
    partitions = 1
)
class AppFunctionalTest {

    @Autowired
    private lateinit var context: ApplicationContext

    @Test
    fun `app starts successfully`() {
        assertThat(context).isNotNull
    }
}