package com.dybek.chuckconsumer.domain

import com.dybek.chuckconsumer.domain.model.Chuck

interface ChuckPublisher {
    fun publish(chuck: Chuck)
}
