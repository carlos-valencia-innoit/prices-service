package com.challenge.pricesservice.domain.port;

import com.challenge.pricesservice.domain.model.PriceDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<PriceDomain> findByDateAndProductIdAndBrandId(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
