package org.example.supplychainx.service.production;


import org.example.supplychainx.dto.production.ProductionOrderDTO;
import java.util.List;

public interface ProductionOrderService {
    ProductionOrderDTO create(ProductionOrderDTO dto);
    ProductionOrderDTO update(Long id, ProductionOrderDTO dto);
    void delete(Long id);
    ProductionOrderDTO getById(Long id);
    List<ProductionOrderDTO> getAll();

}
