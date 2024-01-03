package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.infrastructure.exception.PriceNotFoundException;
import com.challenge.pricesservice.infrastructure.persistence.mapper.PriceMapper;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository jpaRepository;

    @Mock
    private PriceMapper mapper;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByDateAndProductIdAndBrandIdShouldReturnPrices() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();
        PriceEntity mockEntity = createDummyPriceEntity();
        PriceDomain mockPriceDomain = createDummyPrice();

        when(jpaRepository.findTopByProductIdAndBrandIdAndApplicationDateSortedByPriority(productId, brandId, applicationDate))
                .thenReturn(Optional.of(mockEntity));
        when(mapper.toDomain(mockEntity)).thenReturn(mockPriceDomain);

        PriceDomain result = adapter.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);

        assertEquals(mockPriceDomain.id(), result.id());
        assertEquals(mockPriceDomain.brandId(), result.brandId());
        assertEquals(mockPriceDomain.startDate(), result.startDate());
        assertEquals(mockPriceDomain.endDate(), result.endDate());
        assertEquals(mockPriceDomain.priceList(), result.priceList());
        assertEquals(mockPriceDomain.productId(), result.productId());
        assertEquals(mockPriceDomain.priority(), result.priority());
        assertEquals(0, mockPriceDomain.price().compareTo(result.price()));
        assertEquals(mockPriceDomain.currency(), result.currency());
    }

    @Test
    void findByDateAndProductIdAndBrandIdShouldThrowPriceNotFoundException() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(jpaRepository.findTopByProductIdAndBrandIdAndApplicationDateSortedByPriority(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        Executable action = () -> adapter.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);

        assertThrows(PriceNotFoundException.class, action);
    }

    public static PriceEntity createDummyPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(UUID.randomUUID());
        priceEntity.setBrandId(1);
        priceEntity.setStartDate(LocalDateTime.now());
        priceEntity.setEndDate(LocalDateTime.now());
        priceEntity.setPriceList(1);
        priceEntity.setProductId(1);
        priceEntity.setPriority(1);
        priceEntity.setPrice(new BigDecimal("99.99"));
        priceEntity.setCurrency("EUR");
        return priceEntity;
    }

    public static PriceDomain createDummyPrice() {
        return new PriceDomain(
                UUID.randomUUID(),
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1,
                1,
                1,
                new BigDecimal("99.99"),
                "EUR"
        );
    }
}
