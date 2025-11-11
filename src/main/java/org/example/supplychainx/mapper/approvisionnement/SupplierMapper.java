package org.example.supplychainx.mapper.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import org.example.supplychainx.model.approvisionnement.RawMaterial;
import org.example.supplychainx.model.approvisionnement.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "materialIds", expression = "java(mapMaterialsToIds(supplier.getMaterials()))")
    SupplierDTO toDto(Supplier supplier);

    @Mapping(target = "materials", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Supplier toEntity(SupplierDTO dto);

    default List<Long> mapMaterialsToIds(List<RawMaterial> materials) {
        if (materials == null) {
            return List.of();
        }
        return materials.stream()
                .map(RawMaterial::getIdMaterial)
                .collect(Collectors.toList());
    }
}
