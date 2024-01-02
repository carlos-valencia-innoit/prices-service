package com.challenge.pricesservice.domain.port;

import com.challenge.pricesservice.domain.model.Price;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceRepositoryPort {
    Price save(Price price);

    Optional<Price> findById(UUID id);

    List<Price> findAll();

    void deleteById(UUID id);
}
