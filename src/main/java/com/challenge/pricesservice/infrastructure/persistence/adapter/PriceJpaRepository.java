package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, UUID> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND p.startDate <= :applicationDate " +
            "AND p.endDate >= :applicationDate " +
            "ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findTopByProductIdAndBrandIdAndApplicationDateSortedByPriority(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
