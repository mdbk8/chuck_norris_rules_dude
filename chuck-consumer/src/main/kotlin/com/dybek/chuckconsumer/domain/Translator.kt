package com.dybek.chuckconsumer.domain

interface Translator {

    fun translate(wordToTranslate: String): Map<String, String>
}
