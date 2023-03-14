package com.toob.barcorde.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.toob.barcorde.model.BarcodeResponse
import com.toob.barcorde.props.QrCodeFileSpecs
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-13
 *
 * Service class that contains business logic for various Barcode Processing.
 */


@Service
class BarcodeService(
    private val qrCodeWriter: QRCodeWriter,
    private val qrCodeFileSpecs: QrCodeFileSpecs
) {

    /**
     * Class statics section
     */
    companion object {
        const val QR_HEX_ON_BLACK : Int = 0xFF000000.toInt()
        const val QR_HEX_ON_WHITE : Int = 0xFFFFFFFF.toInt()
    }

    /**
     * A Function used to take in the String or Text as an argument.
     * This text will be the one to encode.
     * The encoding will be done into a QR code File while will be produces as a ByteArray
     */
    fun generateQrCodeImage( contentToEncode: String): BarcodeResponse {
        val outStream = ByteArrayOutputStream()

        // Encoding process, with the file properties and content to encode.
        val bitMatrix: BitMatrix = qrCodeWriter.encode(
            contentToEncode,
            BarcodeFormat.QR_CODE,
            qrCodeFileSpecs.width,
            qrCodeFileSpecs.height
        )

        // Image colour processing configuration
        val matrixToImageConfig = MatrixToImageConfig( QR_HEX_ON_BLACK, QR_HEX_ON_WHITE)

        // Write the encoded data to an output Stream that we can use to pass our data around.
        MatrixToImageWriter.writeToStream( bitMatrix, qrCodeFileSpecs.extension, outStream, matrixToImageConfig)

        // Convert our stream data to a ByteArray which is easier to manage when passing accross HTTP
        val encoded: ByteArray = outStream.toByteArray()
        return BarcodeResponse( contentToEncode, encoded)
    }
}
