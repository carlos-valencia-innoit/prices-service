package com.challenge.pricesservice.application.controller;

import com.challenge.pricesservice.api.DefaultApi;
import com.challenge.pricesservice.model.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PricesController implements DefaultApi {


    @Override
    public ResponseEntity<PriceResponse> pricesGet(
            LocalDate applicationDate,
            String productId,
            String chainId) {

        return ResponseEntity.ok(new PriceResponse());
    }
}
