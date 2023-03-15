package com.toob.barcorde.service

import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BarcodeServiceTest {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    lateinit var barcodeService: BarcodeService


    @Test
    fun `Test That We Can Encode & Decode Content From QR Code File`() {
        var barcodeResponse = barcodeService.encodeToQR("Thabo Matjuda")
        assertTrue( barcodeResponse.content.isNotBlank())
        assertTrue( barcodeResponse.encoded.isNotEmpty())
        assertEquals("Thabo Matjuda", barcodeResponse.content)
        log.info("The context data : ${barcodeResponse.content} has been encoded into a QR Code Image ByteArray : ${String(barcodeResponse.encoded)}")

        val decoded = barcodeService.decodeFromQR(barcodeResponse.encoded)
        assertTrue( decoded.isNotEmpty())
        assertEquals("Thabo Matjuda", decoded)
        log.info("Now we have decoded the content : ${String(barcodeResponse.encoded)} to the original content : $decoded")
    }
}
