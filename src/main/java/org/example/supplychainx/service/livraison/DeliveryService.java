package org.example.supplychainx.service.livraison;


import org.example.supplychainx.dto.livraison.DeliveryDTO;

import java.util.List;

public interface DeliveryService {
    DeliveryDTO create(DeliveryDTO dto);
    DeliveryDTO update(Long id, DeliveryDTO dto);
    void delete(Long id);
    DeliveryDTO getById(Long id);
    List<DeliveryDTO> getAll();
}


