package org.example.supplychainx.mapper.livraison;

import org.example.supplychainx.dto.livraison.CustomerDTO;
import org.example.supplychainx.model.livraison.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDto(Customer entity);
    Customer toEntity(CustomerDTO dto);
}
