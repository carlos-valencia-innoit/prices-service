package com.challenge.pricesservice.application.mapper;

import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.domain.model.PriceDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {
    PriceDTO priceToPriceDTO(PriceDomain priceDomain);

    PriceDomain priceDTOToPrice(PriceDTO priceDTO);
}
