package org.example.supplychainx.service.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplyOrderDTO;
import java.util.List;

public interface SupplyOrderService {
    SupplyOrderDTO create(SupplyOrderDTO dto);
    SupplyOrderDTO update(Long id, SupplyOrderDTO dto);
    void delete(Long id);
    SupplyOrderDTO getById(Long id);
    List<SupplyOrderDTO> getAll();
}
