package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.domain.port.PriceRepositoryPort;
import com.challenge.pricesservice.infrastructure.exception.PriceNotFoundException;
import com.challenge.pricesservice.infrastructure.persistence.mapper.PriceMapper;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;
    private final PriceMapper mapper;

    @Override
    public PriceDomain findByDateAndProductIdAndBrandId(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        Optional<PriceEntity> priceEntity = jpaRepository.findTopByProductIdAndBrandIdAndApplicationDateSortedByPriority(
                productId, brandId, applicationDate);

        return priceEntity.map(mapper::toDomain)
                .orElseThrow(PriceNotFoundException::new);
    }
}
