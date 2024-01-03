package com.challenge.pricesservice.application.service;

import com.challenge.pricesservice.application.mapper.PriceDtoMapper;
import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.domain.port.PriceRepositoryPort;
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

class PriceDomainQueryServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private PriceDtoMapper priceDtoMapper;

    @InjectMocks
    private PriceQueryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPriceDetailsShouldReturnPriceDTOList() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();
        PriceDomain mockPriceDomain = createDummyPrice();
        PriceDTO mockPriceDTO = createDummyPriceDTO();

        when(priceRepositoryPort.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(Collections.singletonList(mockPriceDomain));
        when(priceDtoMapper.priceToPriceDTO(mockPriceDomain)).thenReturn(mockPriceDTO);

        List<PriceDTO> result = service.getPriceDetails(applicationDate, productId, brandId);

        assertEquals(1, result.size());
        assertPriceDTOEquals(mockPriceDTO, result.get(0));
    }

    private void assertPriceDTOEquals(PriceDTO expected, PriceDTO actual) {
        assertEquals(expected.getProductId(), actual.getProductId());
        assertEquals(expected.getBrandId(), actual.getBrandId());
        assertEquals(expected.getPriceList(), actual.getPriceList());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(0, expected.getPrice().compareTo(actual.getPrice()));
    }

    private PriceDomain createDummyPrice() {
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


    private PriceDTO createDummyPriceDTO() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProductId(1L);
        priceDTO.setBrandId(1L);
        priceDTO.setPriceList(1);
        priceDTO.setStartDate(LocalDateTime.now());
        priceDTO.setEndDate(LocalDateTime.now());
        priceDTO.setPrice(new BigDecimal("99.99"));
        return priceDTO;
    }
}
