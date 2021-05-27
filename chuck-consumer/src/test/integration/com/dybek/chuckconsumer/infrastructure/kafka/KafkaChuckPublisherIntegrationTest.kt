package com.dybek.chuckconsumer.infrastructure.kafka

import com.dybek.chuckconsumer.domain.ChuckPublisher
import com.dybek.chuckconsumer.domain.model.Chuck
import com.dybek.chuckconsumer.kafka.KafkaProducerTestHelper
import com.dybek.chuckconsumer.kafka.KafkaTestHelperConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext

@EmbeddedKafka(
    topics = ["\${output.topic.name}"],
    partitions = 1
)
@SpringBootTest(
    properties = [
        "output.topic.name=chuck-translated",
        "spring.kafka.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"
    ],
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    classes = [KafkaChuckPublisherConfiguration::class, KafkaTestHelperConfig::class]
)
@DirtiesContext
class KafkaChuckPublisherIntegrationTest {

    @Autowired
    private lateinit var kafkaProducerTestHelper: KafkaProducerTestHelper

    @Autowired
    private lateinit var chuckPublisher: ChuckPublisher

    @Test
    fun `publishes Chuck instance to output Kafka topic`() {
        val key = 1.toString()
        val sentChuck = Chuck(fruit = "banana-translated")

        chuckPublisher.publish(sentChuck)

        val consumedMessage =
            kafkaProducerTestHelper.consumeExpectedNumberOfMessagesFilteredByKey(
                key = key,
                expectedNumberOfMessages = 1
            ).first()

        Assertions.assertThat(consumedMessage.value).isEqualTo(sentChuck)
    }
}