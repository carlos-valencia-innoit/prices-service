package com.challenge.pricesservice.application.service;

import com.challenge.pricesservice.application.mapper.PriceDtoMapper;
import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.domain.port.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceQueryService {

    private final PriceDtoMapper priceDtoMapper;
    private final PriceRepositoryPort priceRepositoryPort;

    public PriceDTO getPriceDetails(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        PriceDomain priceDomain = priceRepositoryPort.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
        return priceDtoMapper.priceToPriceDTO(priceDomain);
    }
}
