package com.ciscodeto.app4reinos

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.toByteArrayBase64(): ByteArray {
    return Base64.decode(this)
}

@OptIn(ExperimentalEncodingApi::class)
fun ByteArray.toBase64String(): String {
    return Base64.encode(this)
}