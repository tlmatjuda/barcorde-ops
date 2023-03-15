package com.toob.barcorde.model


/**
 * Data Bean to be used as a Response to the client or caller of the HTTP REST API.
 */
data class BarcodeResponse(
    val content: String,
    val encoded: ByteArray,
)
