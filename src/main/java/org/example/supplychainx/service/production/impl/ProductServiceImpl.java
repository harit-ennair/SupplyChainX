package org.example.supplychainx.service.production.impl;

import org.example.supplychainx.dto.production.ProductDTO;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.mapper.production.ProductMapper;
import org.example.supplychainx.model.production.Product;
import org.example.supplychainx.repository.production.ProductRepository;
import org.example.supplychainx.repository.production.ProductionOrderRepository;
import org.example.supplychainx.service.production.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductionOrderRepository productionOrderRepository;
    private final ProductMapper mapper;

    @Override
    public ProductDTO create(ProductDTO dto) {

        boolean exists = productRepository.existsByNameIgnoreCase(dto.getName());
        if (exists) {
            throw new BusinessException("Un produit avec ce nom existe déjà : " + dto.getName());
        }

        Product product = mapper.toEntity(dto);
        product.setStock(dto.getStock() != null ? dto.getStock() : 0);

        return mapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produit introuvable (ID=" + id + ")"));

        product.setName(dto.getName());
        product.setCost(dto.getCost());
        product.setStock(dto.getStock());
        product.setProductionTime(dto.getProductionTime());

        return mapper.toDto(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produit introuvable"));

        boolean hasOrders = productionOrderRepository.existsByProductIdProduct(id);
        if (hasOrders) {
            throw new BusinessException("Impossible de supprimer le produit : des ordres de production existent.");
        }

        productRepository.delete(product);
    }

    @Override
    public ProductDTO getById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Produit introuvable !"));
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
