package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, UUID> {
}
