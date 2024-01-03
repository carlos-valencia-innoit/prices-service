package com.challenge.pricesservice.infrastructure.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPriceAt1000On14th() throws Exception {
        performRequest("2020-06-14T10:00:00Z", "35455", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", Matchers.closeTo(35.50, 0.001)));
    }

    @Test
    void testPriceAt1600On14th() throws Exception {
        performRequest("2020-06-14T16:00:00Z", "35455", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice").value("25.45"));
    }

    @Test
    void testPriceAt2100On14th() throws Exception {
        performRequest("2020-06-14T21:00:00Z", "35455", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", Matchers.closeTo(35.50, 0.001)));;
    }

    @Test
    void testPriceAt1000On15th() throws Exception {
        performRequest("2020-06-15T10:00:00Z", "35455", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", Matchers.closeTo(30.50, 0.001)));
    }

    @Test
    void testPriceAt2100On16th() throws Exception {
        performRequest("2020-06-16T21:00:00Z", "35455", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice").value("38.95"));
    }

    private ResultActions performRequest(String dateTime, String productId, String brandId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("applicationDate", dateTime)
                .param("productId", productId)
                .param("chainId", brandId)
                .contentType(MediaType.APPLICATION_JSON));
    }
}
