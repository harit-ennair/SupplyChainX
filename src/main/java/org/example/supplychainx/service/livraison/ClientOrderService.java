package org.example.supplychainx.service.livraison;

import org.example.supplychainx.dto.livraison.ClientOrderDTO;
import java.util.List;

public interface ClientOrderService {
    ClientOrderDTO create(ClientOrderDTO dto);
    ClientOrderDTO update(Long id, ClientOrderDTO dto);
    void delete(Long id);
    ClientOrderDTO getById(Long id);
    List<ClientOrderDTO> getAll();

}

