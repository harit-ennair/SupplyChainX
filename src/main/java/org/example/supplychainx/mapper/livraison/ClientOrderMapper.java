package org.example.supplychainx.mapper.livraison;

import org.example.supplychainx.dto.livraison.ClientOrderDTO;
import org.example.supplychainx.model.livraison.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientOrderMapper {

    @Mapping(source = "customer.idCustomer", target = "customerId")
    @Mapping(source = "product.idProduct", target = "productId")
    ClientOrderDTO toDto(Order entity);

    @Mapping(source = "customerId", target = "customer.idCustomer")
    @Mapping(source = "productId", target = "product.idProduct")
    Order toEntity(ClientOrderDTO dto);
}
