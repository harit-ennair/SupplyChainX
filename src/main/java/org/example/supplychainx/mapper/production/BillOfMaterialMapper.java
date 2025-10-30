package org.example.supplychainx.mapper.production;

import org.example.supplychainx.dto.production.BillOfMaterialDTO;
import org.example.supplychainx.model.production.BillOfMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillOfMaterialMapper {

    @Mapping(source = "material.idMaterial", target = "materialId")
    BillOfMaterialDTO toDto(BillOfMaterial entity);

    @Mapping(source = "materialId", target = "material.idMaterial")
    BillOfMaterial toEntity(BillOfMaterialDTO dto);
}
