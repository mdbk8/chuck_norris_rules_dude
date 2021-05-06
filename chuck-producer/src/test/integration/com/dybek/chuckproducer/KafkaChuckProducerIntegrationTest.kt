package com.dybek.chuckproducer

import com.dybek.chuckproducer.domain.Chuck
import com.dybek.chuckproducer.domain.ChuckPublisher
import com.dybek.chuckproducer.infrastructure.kafka.ChuckPublisherConfiguration
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext

@EmbeddedKafka(
    topics = ["\${topic.name}"],
    partitions = 1
)
@SpringBootTest(
    properties = [
        "topic.name=chuck",
        "topic.numOfPartitions=1",
        "topic.replicationFactor=1",
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ],
    webEnvironment = WebEnvironment.NONE,
    classes = [ChuckPublisherConfiguration::class, KafkaTestHelperConfig::class]
)
@DirtiesContext
class KafkaChuckProducerIntegrationTest {

    @Autowired
    private lateinit var chuckPublisher: ChuckPublisher

    @Autowired
    private lateinit var consumerTestHelper: KafkaConsumerTestHelper

    @Test
    fun `publishes Chuck on Kafka topic`() {
        val expectedChuck = Chuck(
            id = "4",
            fact = "Chuck is cool",
            beer = "Pale Ale",
            fruit = "banana",
            city = "Cracow",
            country = "Spain"
        )
        chuckPublisher.publish(expectedChuck)

        val actuallySentChuck = consumerTestHelper.consumeExpectedNumberOfMessagesFilteredByKey(
            expectedChuck.id,
            1
        )[0].value

        assertThat(actuallySentChuck).isEqualTo(expectedChuck)
    }
}