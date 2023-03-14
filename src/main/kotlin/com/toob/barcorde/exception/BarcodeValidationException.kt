package com.toob.barcorde.exception


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 * Custom Exception to be used when validations fail.
 * For The HTTP REST API this would be for 400 Bad Requests
 */


class BarcodeValidationException( override val message: String?) : RuntimeException(message)
