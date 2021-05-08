package com.dybek.chuckproducer

import com.dybek.chuckproducer.domain.Chuck
import com.dybek.chuckproducer.domain.ChuckGenerator
import com.dybek.chuckproducer.domain.MockChuckGeneratorTestConfig
import com.dybek.chuckproducer.interfaces.RestHelperTestConfig
import com.dybek.chuckproducer.interfaces.RestRequestSender
import com.dybek.chuckproducer.kafka.KafkaConsumerTestHelper
import com.dybek.chuckproducer.kafka.KafkaTestHelperConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.ApplicationContext
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    properties = [
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ],
    classes = [
        ChuckProducerApplication::class,
        KafkaTestHelperConfig::class,
        RestHelperTestConfig::class,
        MockChuckGeneratorTestConfig::class
    ]
)
@EmbeddedKafka(
    topics = ["\${topic.name}"],
    partitions = 1
)
class AppFunctionalTest {

    @Autowired private lateinit var context: ApplicationContext
    @Autowired private lateinit var restRequestSender: RestRequestSender
    @Autowired private lateinit var consumerTestHelper: KafkaConsumerTestHelper
    @Autowired private lateinit var mockChuckGenerator: ChuckGenerator

    @Test
    fun `app starts successfully`() {
        assertThat(context).isNotNull
    }

    @Test
    fun `generates and publishes Chuck on output Kafka topic`() {
        val expectedChuck = Chuck(
            id = "1",
            fact = "Chuck is awesome",
            beer = "Wheat IPA",
            fruit = "orange",
            city = "Madrid",
            country = "Germany"
        )
        whenever(mockChuckGenerator.generate()).thenReturn(expectedChuck)

        restRequestSender.performPostRequest("/produce")

        val actuallySentChuck = consumerTestHelper.consumeExpectedNumberOfMessagesFilteredByKey(
            expectedChuck.id,
            1
        )[0].value
        assertThat(actuallySentChuck).isEqualTo(expectedChuck)
    }
}