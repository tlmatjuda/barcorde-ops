package com.toob.barcorde.service

import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Slf4j
@SpringBootTest
class BarcodeServiceTest {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    lateinit var barcodeService: BarcodeService

    @Test
    fun `Test That I Can Generate QR Code Image As BarcodeResponse`() {
        assertNotNull( barcodeService)
        val barcodeResponse = barcodeService.generateQrCodeImage("Thabo Matjuda")
        assertNotNull( barcodeResponse.contentArg)
        assertNotNull( barcodeResponse.encoded)

        assertEquals("Thabo Matjuda", barcodeResponse.contentArg)
    }
}
