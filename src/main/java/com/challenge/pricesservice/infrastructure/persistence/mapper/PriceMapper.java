package com.challenge.pricesservice.infrastructure.persistence.mapper;

import com.challenge.pricesservice.domain.model.PriceDomain;
import com.challenge.pricesservice.infrastructure.persistence.model.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceEntity toEntity(PriceDomain priceDomain);

    PriceDomain toDomain(PriceEntity priceEntity);
}
