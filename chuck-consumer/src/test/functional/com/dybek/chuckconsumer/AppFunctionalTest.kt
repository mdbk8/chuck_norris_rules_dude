package com.dybek.chuckconsumer

import com.dybek.chuckconsumer.domain.ChuckPublisher
import com.dybek.chuckconsumer.domain.MockedChuckPublisherTestConfig
import com.dybek.chuckconsumer.domain.model.Chuck
import com.dybek.chuckconsumer.kafka.KafkaProducerTestHelper
import com.dybek.chuckconsumer.kafka.KafkaTestHelperConfig
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    properties = [
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ],
    classes = [
        ChuckConsumerApplication::class,
        MockedChuckPublisherTestConfig::class,
        KafkaTestHelperConfig::class
    ]
)
@EmbeddedKafka(
    topics = ["\${input.topic.name}"],
    partitions = 1
)
@DirtiesContext
class AppFunctionalTest {

    @Autowired
    private lateinit var mockChuckPublisher: ChuckPublisher

    @Autowired
    private lateinit var kafkaConsumerTestHelper: KafkaProducerTestHelper

    @Test
    fun `reads data from Kafka input topic, translates fruit name and publishes to output Kafka topic`() {
        val key = 1.toString()
        val fruit = "banana"
        val sentChuck = Chuck(fruit = fruit)

        kafkaConsumerTestHelper.sendMessageAndWaitToAppear(key, sentChuck)

        verify(mockChuckPublisher).publish(sentChuck.copy(fruit = "$fruit-translated"))
    }
}