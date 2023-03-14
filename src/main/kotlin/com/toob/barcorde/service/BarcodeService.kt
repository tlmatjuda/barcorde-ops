package com.toob.barcorde.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.toob.barcorde.model.BarcodeResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-13
 */


@Service
class BarcodeService(
    private val qrCodeWriter: QRCodeWriter
) {

    companion object {
        const val QR_HEX_ON_BLACK : Long = 0xFF000000
        const val QR_HEX_ON_WHITE : Long = 0xFFFFFFFF
    }

    fun generateQrCodeImage(contentToEncode: String): BarcodeResponse {
        val outStream = ByteArrayOutputStream()
        val bitMatrix: BitMatrix = qrCodeWriter.encode(contentToEncode, BarcodeFormat.QR_CODE, 250, 250)
        val matrixToImageConfig = MatrixToImageConfig(QR_HEX_ON_BLACK.toInt(), QR_HEX_ON_WHITE.toInt())
        MatrixToImageWriter.writeToStream( bitMatrix, "PNG", outStream, matrixToImageConfig)

        return BarcodeResponse( contentToEncode, outStream.toByteArray())
    }

    private fun qrFileAsByteArray(contentToEncode: String): ByteArray {
        val outStream = ByteArrayOutputStream()
        val bitMatrix: BitMatrix = qrCodeWriter.encode(contentToEncode, BarcodeFormat.QR_CODE, 250, 250)
        val matrixToImageConfig = MatrixToImageConfig(QR_HEX_ON_BLACK.toInt(), QR_HEX_ON_WHITE.toInt())
        MatrixToImageWriter.writeToStream( bitMatrix, "PNG", outStream, matrixToImageConfig)

        return outStream.toByteArray()
    }
}
