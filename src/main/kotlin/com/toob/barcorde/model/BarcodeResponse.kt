package com.toob.barcorde.model

data class BarcodeResponse(
    val contentToEncode: String,
    val encoded: ByteArray
)
