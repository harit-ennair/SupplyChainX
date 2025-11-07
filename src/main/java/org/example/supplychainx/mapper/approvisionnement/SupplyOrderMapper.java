package org.example.supplychainx.mapper.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplyOrderDTO;
import org.example.supplychainx.model.approvisionnement.SupplyOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SupplyMaterialMapper.class })
public interface SupplyOrderMapper {

    @Mapping(source = "supplier.idSupplier", target = "supplierId")
    @Mapping(source = "supplyMaterials", target = "materials")
    SupplyOrderDTO toDto(SupplyOrder entity);

    @Mapping(source = "supplierId", target = "supplier.idSupplier")
    @Mapping(source = "materials", target = "supplyMaterials")
    SupplyOrder toEntity(SupplyOrderDTO dto);
}
