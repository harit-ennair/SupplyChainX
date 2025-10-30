package org.example.supplychainx.service.livraison;

import org.example.supplychainx.dto.livraison.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO create(CustomerDTO dto);
    CustomerDTO update(Long id, CustomerDTO dto);
    void delete(Long id);
    CustomerDTO getById(Long id);
    List<CustomerDTO> getAll();
}
