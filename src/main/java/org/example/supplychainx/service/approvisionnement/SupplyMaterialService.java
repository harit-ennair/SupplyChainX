package org.example.supplychainx.service.approvisionnement;


import org.example.supplychainx.dto.approvisionnement.SupplyMaterialDTO;
import java.util.List;

public interface SupplyMaterialService {

    SupplyMaterialDTO create(SupplyMaterialDTO dto);
    SupplyMaterialDTO update(Long id, SupplyMaterialDTO dto);
    void delete(Long id);
    SupplyMaterialDTO getById(Long id);
    List<SupplyMaterialDTO> getAll();
}


