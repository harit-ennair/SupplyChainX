package org.example.supplychainx.service.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.RawMaterialDTO;
import java.util.List;

public interface RawMaterialService {
    RawMaterialDTO create(RawMaterialDTO dto);
    RawMaterialDTO update(Long id, RawMaterialDTO dto);
    void delete(Long id);
    RawMaterialDTO getById(Long id);
    List<RawMaterialDTO> getAll();
}
