package com.toob.barcorde.rest

import com.toob.barcorde.exception.BarcodeTechnicalException
import com.toob.barcorde.exception.BarcodeValidationException
import com.toob.barcorde.model.BarcodeResponse
import com.toob.barcorde.service.BarcodeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 *
 * HTTP REST Resource that will handle requests that come to the base path http://{ip}:{port}/{app-context}/qr/...
 */


@RestController
@RequestMapping(value = ["/qr"])
class BarcodeResource(
    val barcodeService: BarcodeService) {

    /**
     * Declaring statics for this class.
     */
    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }


    /**
     * handles GET requests to the .../genrate path.
     * This will call the business logic of creating a QR Code PNG file.
     * This file will be produces as Base64 Byte Array and returned back to the client toegher with the original content.
     * This would be the content that a user wants to encode into a QR code.
     */
    @GetMapping(value = ["/generate"])
    fun generate( @RequestParam content: String): ResponseEntity<*> {
        val response: ResponseEntity<BarcodeResponse>

        // Validation to make sure we don't just encode blank data.
        if ( content.isBlank())
            throw BarcodeValidationException("The content to encode is required, : [content]")

        // When all is good and we are happy then we can try to encode and see how that goes.
        try {
            val encodedQRImage: BarcodeResponse = barcodeService.generateQrCodeImage(content)
            response = ResponseEntity.ok().body( encodedQRImage)
        } catch (exception: Exception) {
            log.error( exception.message, exception)
            throw BarcodeTechnicalException("Error while generating a QR Image from your content : $content")
        }

        return response;
    }

}
