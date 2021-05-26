package com.dybek.chuckconsumer.interfaces.kafka

import com.dybek.chuckconsumer.domain.ChuckConsumer
import com.dybek.chuckconsumer.domain.MockChuckConsumerTestConfig
import com.dybek.chuckconsumer.domain.model.Chuck
import com.dybek.chuckconsumer.kafka.KafkaProducerTestHelper
import com.dybek.chuckconsumer.kafka.KafkaTestHelperConfig
import org.junit.jupiter.api.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("default")
@SpringBootTest(
    webEnvironment = WebEnvironment.NONE,
    classes = [
        KafkaChuckConsumerConfiguration::class,
        MockChuckConsumerTestConfig::class,
        KafkaTestHelperConfig::class
    ],
    properties = [
        "input.topic.name=chuck",
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ]
)
@EmbeddedKafka(
    topics = ["\${input.topic.name}"],
    partitions = 1
)
@DirtiesContext
class KafkaChuckConsumerIntegrationTest {

    @Autowired
    private lateinit var chuckConsumerMock: ChuckConsumer

    @Autowired
    private lateinit var kafkaConsumerTestHelper: KafkaProducerTestHelper

    @Test
    fun `consumes Chuck instance from Kafka topic`() {
        val key = 1.toString()
        val sentChuck = Chuck(fruit = "banana")

        kafkaConsumerTestHelper.sendMessageAndWaitToAppear(key, sentChuck)

        verify(chuckConsumerMock).consume(eq(sentChuck))
    }
}