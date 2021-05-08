package com.dybek.chuckproducer.interfaces.web

import com.dybek.chuckproducer.domain.ChuckProducer
import com.dybek.chuckproducer.domain.MockChuckProducerTestConfig
import com.dybek.chuckproducer.interfaces.RestHelperTestConfig
import com.dybek.chuckproducer.interfaces.RestRequestSender
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = [
        WebConfiguration::class,
        MockChuckProducerTestConfig::class,
        RestHelperTestConfig::class
    ]
)
class ChuckControllerIntegrationTest {

    @Autowired
    private lateinit var mockChuckProducer: ChuckProducer

    @Autowired
    private lateinit var restRequestSender: RestRequestSender

    @Test
    fun `produces Chuck`() {
        restRequestSender.performPostRequest("/produce")

        verify(mockChuckProducer).produce()
    }

}