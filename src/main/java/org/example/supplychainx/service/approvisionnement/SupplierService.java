package org.example.supplychainx.service.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import java.util.List;

public interface SupplierService {
    SupplierDTO create(SupplierDTO dto);
    SupplierDTO update(Long id, SupplierDTO dto);
    void delete(Long id);
    SupplierDTO getById(Long id);
    List<SupplierDTO> getAll();
}
