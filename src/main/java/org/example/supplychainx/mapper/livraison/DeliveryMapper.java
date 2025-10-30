package org.example.supplychainx.mapper.livraison;

import org.example.supplychainx.dto.livraison.DeliveryDTO;
import org.example.supplychainx.model.livraison.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(source = "order.idOrder", target = "orderId")
    DeliveryDTO toDto(Delivery entity);

    @Mapping(source = "orderId", target = "order.idOrder")
    Delivery toEntity(DeliveryDTO dto);
}
