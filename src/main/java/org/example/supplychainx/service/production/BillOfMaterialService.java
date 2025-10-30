package org.example.supplychainx.service.production;

import org.example.supplychainx.dto.production.BillOfMaterialDTO;
import java.util.List;

public interface BillOfMaterialService {
    BillOfMaterialDTO create(BillOfMaterialDTO dto);
    BillOfMaterialDTO update(Long id, BillOfMaterialDTO dto);
    void delete(Long id);
    BillOfMaterialDTO getById(Long id);
    List<BillOfMaterialDTO> getAll();
}
