package com.toob.barcorde.rest

import com.toob.barcorde.exception.BarcodeTechnicalException
import com.toob.barcorde.model.BarcodeResponse
import com.toob.barcorde.service.BarcodeService
import com.toob.barcorde.validation.BarcodeRestValidator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 */


@RestController
@RequestMapping("/qr")
class BarcodeResource(
    val barcodeService: BarcodeService,
    val barcodeRestValidtor: BarcodeRestValidator) {


    @GetMapping("/generate")
    fun generate( @RequestParam content: String): ResponseEntity<*> {
        val response: ResponseEntity<BarcodeResponse>

        try {
            barcodeRestValidtor.validate(content)

            val encodedQRImage: BarcodeResponse = barcodeService.generateQrCodeImage(content)
            response = ResponseEntity.ok().body( encodedQRImage)
        } catch (exception: Exception) {
            throw BarcodeTechnicalException("Error while generating a QR Image from your content : $content", exception)
        }

        return response;
    }

}
