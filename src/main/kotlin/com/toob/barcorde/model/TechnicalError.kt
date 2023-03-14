package com.toob.barcorde.model


/**
 * Data Bean to be used when we hit or have errors in out HTTP REST API.
 */
data class TechnicalError(
    val error: String,
    val description: String
)
