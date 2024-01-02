package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.domain.model.Price;
import com.challenge.pricesservice.domain.port.PriceRepositoryPort;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;

    @Autowired
    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Price save(Price price) {
        PriceEntity priceEntity = PriceEntity.fromDomain(price);
        return jpaRepository.save(priceEntity).toDomain();
    }

    @Override
    public Optional<Price> findById(UUID id) {
        return jpaRepository.findById(id).map(PriceEntity::toDomain);
    }

    @Override
    public List<Price> findAll() {
        return jpaRepository.findAll().stream()
                .map(PriceEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
