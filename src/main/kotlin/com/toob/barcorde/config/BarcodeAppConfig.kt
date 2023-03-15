package com.toob.barcorde.config

import com.google.zxing.qrcode.QRCodeReader
import com.google.zxing.qrcode.QRCodeWriter
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-13
 * Spring Configuration Class to be used for various Applicatoin Spring Configs.
 */

@Configuration
class BarcodeAppConfig {


    /**
     * QR Code Write from Google's ZXing.
     * Decalring a brean that we will use to write the content to a QR Code image
     */
    @Bean
    fun qrCodeWriter(): QRCodeWriter {
        return QRCodeWriter()
    }


    /**
     * QR Code Reader from Google's ZXing
     * Declaring a bean that we will use to read content from a QR Code Image.
     */
    @Bean
    fun qrCodeReader(): QRCodeReader {
        return QRCodeReader()
    }

}
