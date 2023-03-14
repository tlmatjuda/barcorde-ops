package com.toob.barcorde.exception


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 */

class BarcodeValidationException: RuntimeException {
    constructor ()
    constructor (message: String?)
    constructor (message: String?, cause: Throwable?)
    constructor (cause: Throwable?)
}
