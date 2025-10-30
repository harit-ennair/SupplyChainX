package org.example.supplychainx.mapper.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import org.example.supplychainx.model.approvisionnement.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDto(Supplier supplier);
    Supplier toEntity(SupplierDTO dto);
}
