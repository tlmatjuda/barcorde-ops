package com.toob.barcorde.config

import com.google.zxing.qrcode.QRCodeWriter
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-13
 */

@Configuration
class BarcodeAppConfig {

    @Bean
    fun qrCodeWriter(): QRCodeWriter {
        return QRCodeWriter()
    }

}
