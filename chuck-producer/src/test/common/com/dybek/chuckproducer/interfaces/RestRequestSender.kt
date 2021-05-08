package com.dybek.chuckproducer.interfaces

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

class RestRequestSender(
    private val port: Int,
    private val contextPath: String
) {

    fun performPostRequest(path: String): Response =
        request().post(path).thenReturn()

    private fun request(): RequestSpecification =
        RestAssured.given()
            .baseUri("http://localhost:$port$contextPath")
            .log().all()
}