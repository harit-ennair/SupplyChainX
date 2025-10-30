package org.example.supplychainx.mapper.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.RawMaterialDTO;
import org.example.supplychainx.model.approvisionnement.RawMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {
    RawMaterialDTO toDto(RawMaterial material);
    RawMaterial toEntity(RawMaterialDTO dto);
}
