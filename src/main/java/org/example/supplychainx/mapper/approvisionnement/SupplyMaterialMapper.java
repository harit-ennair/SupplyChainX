package org.example.supplychainx.mapper.approvisionnement;


import org.example.supplychainx.dto.approvisionnement.SupplyMaterialDTO;
import org.example.supplychainx.model.approvisionnement.SupplyMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplyMaterialMapper {

    @Mapping(source = "material.idMaterial", target = "materialId")
    SupplyMaterialDTO toDto(SupplyMaterial entity);

    @Mapping(source = "materialId", target = "material.idMaterial")
    SupplyMaterial toEntity(SupplyMaterialDTO dto);
}
