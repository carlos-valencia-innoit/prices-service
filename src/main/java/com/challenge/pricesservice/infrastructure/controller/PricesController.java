package com.challenge.pricesservice.infrastructure.controller;

import com.challenge.pricesservice.api.DefaultApi;
import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.application.service.PriceQueryService;
import com.challenge.pricesservice.infrastructure.persistence.mapper.PriceMapper;
import com.challenge.pricesservice.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PricesController implements DefaultApi {

    private final PriceQueryService service;
    private final PriceMapper mapper;

    @Override
    public ResponseEntity<PriceResponse> pricesGet(
            OffsetDateTime applicationDate,
            String productId,
            String chainId) {

        PriceDTO priceDetail = service.getPriceDetails(
                LocalDateTime.from(applicationDate), Integer.parseInt(productId),
                Integer.parseInt(chainId));

        return ResponseEntity.ok(mapper.priceDtoToPriceResponse(priceDetail));
    }
}
