package com.challenge.pricesservice.domain.port;

import com.challenge.pricesservice.domain.model.PriceDomain;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    PriceDomain findByDateAndProductIdAndBrandId(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
