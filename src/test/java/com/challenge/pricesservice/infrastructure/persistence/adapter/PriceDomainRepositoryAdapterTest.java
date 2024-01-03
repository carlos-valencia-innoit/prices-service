package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.infrastructure.persistence.mapper.PriceMapper;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PriceDomainRepositoryAdapterTest {

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

        when(jpaRepository.findByProductIdAndBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(Collections.singletonList(mockEntity));
        when(mapper.toDomain(mockEntity)).thenReturn(mockPriceDomain);

        List<PriceDomain> result = adapter.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);

        PriceDomain resultPriceDomain = result.get(0);
        assertEquals(mockPriceDomain.id(), resultPriceDomain.id());
        assertEquals(mockPriceDomain.brandId(), resultPriceDomain.brandId());
        assertEquals(mockPriceDomain.startDate(), resultPriceDomain.startDate());
        assertEquals(mockPriceDomain.endDate(), resultPriceDomain.endDate());
        assertEquals(mockPriceDomain.priceList(), resultPriceDomain.priceList());
        assertEquals(mockPriceDomain.productId(), resultPriceDomain.productId());
        assertEquals(mockPriceDomain.priority(), resultPriceDomain.priority());
        assertEquals(0, mockPriceDomain.price().compareTo(resultPriceDomain.price()));
        assertEquals(mockPriceDomain.currency(), resultPriceDomain.currency());

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
