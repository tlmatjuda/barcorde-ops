package com.toob.barcorde.validation

import com.toob.barcorde.exception.BarcodeValidationException
import org.springframework.stereotype.Component


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 */

@Component
class BarcodeRestValidator {

    fun validate( content: String) {
        if ( content.isBlank())
            throw BarcodeValidationException("The content to encode is required, : [content]")
    }

}
