package org.example.supplychainx.service.production;

import org.example.supplychainx.dto.production.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    void delete(Long id);
    ProductDTO getById(Long id);
    List<ProductDTO> getAll();
}
