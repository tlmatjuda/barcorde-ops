package com.toob.barcorde.exception


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 * Exception used for various Technical Errors, Internal Server Error types.
 */


class BarcodeTechnicalException( override val message: String?) : RuntimeException(message)
