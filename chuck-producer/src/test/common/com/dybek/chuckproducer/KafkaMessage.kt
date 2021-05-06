package com.dybek.chuckproducer

data class KafkaMessage<V> (val key: String, val value: V)
