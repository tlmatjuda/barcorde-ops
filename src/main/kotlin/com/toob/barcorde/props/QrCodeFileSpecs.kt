package com.toob.barcorde.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2023-03-14
 *
 * Configurations of our QR Code File.
 * These are the properties of the file we be producing.
 * They are injected into this Spring Component
 */

@Component
@ConfigurationProperties( prefix = "barcode.service.qr.specification")
data class QrCodeFileSpecs(
    var width: Int = 0,
    var height: Int = 0,
    var extension: String = ""
)


