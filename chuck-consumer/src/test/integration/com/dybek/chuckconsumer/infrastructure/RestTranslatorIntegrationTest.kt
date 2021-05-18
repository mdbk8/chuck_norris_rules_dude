package com.dybek.chuckconsumer.infrastructure

import com.dybek.chuckconsumer.infrastructure.rest.RestInfrastructureConfiguration
import com.dybek.chuckconsumer.infrastructure.rest.RestTranslatorClient
import com.github.tomakehurst.wiremock.WireMockServer
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returns
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(
    classes = [RestInfrastructureConfiguration::class],
    properties = [
        "chuck.translator.url=localhost:\${wiremock.server.port}"
    ],
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@AutoConfigureWireMock(port = 9090)
@DirtiesContext
class RestTranslatorIntegrationTest {

    @Autowired
    private lateinit var server: WireMockServer

    @Autowired
    private lateinit var restTranslatorClient: RestTranslatorClient

    @BeforeEach
    fun setUp() = server.resetAll()

    @Test
    fun `gets translation for given word`() {
        val wordToTranslate = "test"
        server.stubGetTranslateFor(word = wordToTranslate, returnTranslation = "dupa")

        val actualTranslation = restTranslatorClient.translate(wordToTranslate)

        assertThat(actualTranslation).isEqualTo(mapOf(wordToTranslate to "dupa"))
    }
}

fun WireMockServer.stubGetTranslateFor(word: String, returnTranslation: String) {
    get {
        url equalTo "/translate/$word"
    } returns {
        header = HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON_VALUE
        body = """
                { ${word.toJsonValue()} : ${returnTranslation.toJsonValue()} }
            """.trimIndent()
    }
}

fun <T> T.toJsonValue() = "\"$this\""
fun <T> T?.toJsonValueOrNull() = this?.let { "\"$this\"" }