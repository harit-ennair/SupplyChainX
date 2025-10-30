package org.example.supplychainx.mapper.production;

import org.example.supplychainx.dto.production.ProductionOrderDTO;
import org.example.supplychainx.model.production.ProductionOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductionOrderMapper {

    @Mapping(source = "product.idProduct", target = "productId")
    ProductionOrderDTO toDto(ProductionOrder entity);

    @Mapping(source = "productId", target = "product.idProduct")
    ProductionOrder toEntity(ProductionOrderDTO dto);
}
