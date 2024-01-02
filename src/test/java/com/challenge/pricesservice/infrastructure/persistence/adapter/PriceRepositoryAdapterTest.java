package com.challenge.pricesservice.infrastructure.persistence.adapter;

import com.challenge.pricesservice.domain.model.Price;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository jpaRepository;

    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new PriceRepositoryAdapter(jpaRepository);
    }

    @Test
    void testSave() {
        Price price = new Price();
        PriceEntity priceEntity = PriceEntity.fromDomain(price);
        when(jpaRepository.save(any(PriceEntity.class))).thenReturn(priceEntity);

        Price savedPrice = adapter.save(price);
        assertNotNull(savedPrice);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        PriceEntity priceEntity = new PriceEntity();
        when(jpaRepository.findById(id)).thenReturn(Optional.of(priceEntity));

        Optional<Price> foundPrice = adapter.findById(id);
        assertTrue(foundPrice.isPresent());
    }

    @Test
    void testFindAll() {
        List<PriceEntity> priceEntities = List.of(new PriceEntity()); // Añadir entidades con datos de prueba
        when(jpaRepository.findAll()).thenReturn(priceEntities);

        List<Price> prices = adapter.findAll();
        assertNotNull(prices);
        assertEquals(priceEntities.size(), prices.size());
    }


    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        adapter.deleteById(id);
        verify(jpaRepository).deleteById(id);
    }

}
