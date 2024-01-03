package com.challenge.pricesservice.infrastructure.persistence.mapper;

import com.challenge.pricesservice.application.model.PriceDTO;
import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import com.challenge.pricesservice.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceDomain toDomain(PriceEntity priceEntity);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "chainId", source = "brandId")
    @Mapping(target = "tariff", source = "priceList")
    @Mapping(target = "applicationDates", expression = "java(java.util.Arrays.asList(priceDTO.getStartDate().toLocalDate(), priceDTO.getEndDate().toLocalDate()))")
    @Mapping(target = "finalPrice", source = "price")
    PriceResponse priceDtoToPriceResponse(PriceDTO priceDTO);
}
