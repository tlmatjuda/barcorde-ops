package com.toob.barcorde.rest

import com.ninjasquad.springmockk.MockkBean
import com.toob.barcorde.model.BarcodeResponse
import com.toob.barcorde.service.BarcodeService
import io.mockk.clearMocks
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.lang.RuntimeException

@WebMvcTest
class BarcodeResourceTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var barcodeService: BarcodeService


    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @BeforeEach
    fun setUp() {
        clearMocks( barcodeService)
    }

    @Test
    fun `Test Bad Request When There's No Argument`() {
        mockMvc.perform( get("/qr/generate?content="))
            .andExpect( status().isBadRequest)
            .andExpect( content().contentType(MediaType.APPLICATION_JSON))
            .andExpect( jsonPath("$.error").value("Bad Request"))
    }

    @Test
    fun `Test Internal Server Error When There's No Argument`() {

        // Our test HTTP Query Param
        val contextArgument = "There's Content Now"

        // Mockked Business Logic behaviour
        every { barcodeService.encodeToQR( contextArgument) } throws RuntimeException("Mockked Runtime Exception in our Service Business Logic for Testing purposes")

        // Run the HTTP Request
        val httpResponse = mockMvc.perform(get("/qr/generate?content=$contextArgument"))
            .andExpect(status().isInternalServerError)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        val expectedJsonResponse = httpResponse?.response?.contentAsString
        assertTrue( expectedJsonResponse!!.isNotBlank())
        log.info("We have the expected JSON Response which is : \n\n$expectedJsonResponse")
    }

    @Test
    fun `Test Should Be Successful When Request Is Valid`() {

        // Our test HTTP Query Param
        val contextArgument = "TLMatjuda"
        val encoded = """
            iVBORw0KGgoAAAANSUhEUgAAAPoAAAD6AQAAAACgl2eQAAAAzUlEQVR4Xu3SUQ7DMAgDUG5OjtabZdimzVRpu
            4DxqjTA6w9a7P+54t15ZYAyQBmgDFC8wAomectT2QEWC6OssytHoNHWop7KFaxI3O3B5uMMmjU5PTMQTHJJWBMrP
            /BE2/mKF+BqsKDcvatsaggCLdwkFS+AQbWz1oRHtSNYeS9H73vuBrieUv07wg0oYhzjtd1A/UuQ5EAiLAGrOh
            f79J6geskTAzJ9ZApIoDS2BbUhsI2LJWgGyD2ZArbVJ4Y1BL8zQBmgDFAGKAOU6wMx4NukULG1dAAAAABJRU5ErkJggg==
        """.toByteArray()

        // Mockked Business Logic behaviour
        every { barcodeService.encodeToQR( contextArgument) } returns BarcodeResponse( contextArgument, encoded)

        // Run the HTTP Request
        val getOperation = get("/qr/generate")
            .param("content", contextArgument)

        val httpResponse = mockMvc.perform(getOperation)
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        val expectedJsonResponse = httpResponse.response.contentAsString
        assertTrue( expectedJsonResponse.isNotBlank())
        log.info("We have the expected JSON Response which is : \n\n$expectedJsonResponse")
    }
}
