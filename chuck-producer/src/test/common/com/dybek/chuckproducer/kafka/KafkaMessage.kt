package com.dybek.chuckproducer.kafka

data class KafkaMessage<V> (val key: String, val value: V)
