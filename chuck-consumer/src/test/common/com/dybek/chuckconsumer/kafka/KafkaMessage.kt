package com.dybek.chuckconsumer.kafka

data class KafkaMessage<V> (val key: String, val value: V)
