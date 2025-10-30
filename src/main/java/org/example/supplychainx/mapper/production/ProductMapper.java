package org.example.supplychainx.mapper.production;

import org.example.supplychainx.dto.production.ProductDTO;
import org.example.supplychainx.model.production.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product entity);
    Product toEntity(ProductDTO dto);
}
