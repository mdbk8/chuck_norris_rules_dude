package com.dybek.chuckconsumer.kafka

import com.dybek.chuckconsumer.domain.model.Chuck
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import java.time.Duration
import java.util.function.Consumer

class KafkaTestConsumer(
    private val consumer: KafkaConsumer<String, Chuck>,
    private val topic: String,
    private val defaultPoolDuration: Duration = Duration.ofSeconds(2)
) {

    fun consumeAllFromTheBeginning(): ArrayList<KafkaMessage<Chuck>> {
        val partitions = getTopicPartitions()

        consumer.assign(partitions)
        consumer.seekToBeginning(partitions)

        val messages = ArrayList<KafkaMessage<Chuck>>()
        val endOffsets = consumer.endOffsets(partitions)

        partitions.forEach(Consumer { partition ->
            val partitionMessages = readPartition(partition, endOffsets[partition]!!)
            messages.addAll(partitionMessages)
        })

        return messages
    }

    private fun getTopicPartitions() =
        consumer.partitionsFor(topic).map { TopicPartition(it.topic(), it.partition()) }

    private fun readPartition(partition: TopicPartition, endOffset: Long): Collection<KafkaMessage<Chuck>> {
        val partitionMessages = ArrayList<KafkaMessage<Chuck>>()
        while (consumer.position(partition) < endOffset) {
            consumer.poll(defaultPoolDuration).forEach(Consumer {
                partitionMessages.add(KafkaMessage(it.key(), it.value()))
            })
        }

        return partitionMessages
    }

    fun close() = consumer.close()
}
