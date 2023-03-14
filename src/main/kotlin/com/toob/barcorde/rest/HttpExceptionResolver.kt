package com.toob.barcorde.rest

import com.toob.barcorde.exception.BarcodeTechnicalException
import com.toob.barcorde.exception.BarcodeValidationException
import com.toob.barcorde.model.TechnicalError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 */


@RestControllerAdvice
class HttpExceptionResolver {

    @ExceptionHandler( value = [(NoHandlerFoundException::class)] )
    fun handleNotFound(noHandlerFoundException: NoHandlerFoundException, request: WebRequest): ResponseEntity<TechnicalError> {
        val technicalError = TechnicalError(HttpStatus.NOT_FOUND.reasonPhrase, noHandlerFoundException.localizedMessage)
        return ResponseEntity( technicalError, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler( value = [(BarcodeTechnicalException::class)] )
    fun handleTechnicalError(barcodeTechnicalException: BarcodeTechnicalException, request: WebRequest): ResponseEntity<TechnicalError> {
        val technicalError = TechnicalError(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase, barcodeTechnicalException.localizedMessage)
        return ResponseEntity( technicalError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler( value = [ (BarcodeValidationException::class)] )
    fun handleBadRequest(barcodeValidationException: BarcodeValidationException, request: WebRequest): ResponseEntity<TechnicalError> {
        val technicalError = TechnicalError(HttpStatus.BAD_REQUEST.reasonPhrase, barcodeValidationException.localizedMessage)
        return ResponseEntity( technicalError, HttpStatus.BAD_REQUEST)
    }

}
