package com.challenge.pricesservice.application.service;

import com.challenge.pricesservice.application.mapper.PriceDtoMapper;
import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.domain.port.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceQueryService {

    private final PriceDtoMapper priceDtoMapper;
    private final PriceRepositoryPort priceRepositoryPort;

    public List<PriceDTO> getPriceDetails(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        List<PriceDomain> priceDomains = priceRepositoryPort.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
        return priceDomains.stream()
                .map(priceDtoMapper::priceToPriceDTO)
                .toList();
    }
}
