package org.example.supplychainx.service.production.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.production.ProductionOrderDTO;
import org.example.supplychainx.mapper.production.ProductionOrderMapper;
import org.example.supplychainx.model.approvisionnement.RawMaterial;
import org.example.supplychainx.model.production.BillOfMaterial;
import org.example.supplychainx.model.production.ProductionOrder;
import org.example.supplychainx.model.production.ProductionStatusEnum;
import org.example.supplychainx.repository.approvisionnement.RawMaterialRepository;
import org.example.supplychainx.repository.production.BillOfMaterialRepository;
import org.example.supplychainx.repository.production.ProductionOrderRepository;
import org.example.supplychainx.service.production.ProductionOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionOrderServiceImpl implements ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final BillOfMaterialRepository billRepository;
    private final ProductionOrderMapper mapper;

    @Override
    public ProductionOrderDTO create(ProductionOrderDTO dto) {

        List<BillOfMaterial> boms = billRepository.findByProductIdProduct(dto.getProductId());
        for (BillOfMaterial bom : boms) {
            RawMaterial material = bom.getMaterial();
            int required = bom.getQuantity() * dto.getQuantity();
            if (material.getStock() < required) {
                throw new RuntimeException("Stock insuffisant pour la matière : " + material.getName());
            }
        }

        ProductionOrder order = mapper.toEntity(dto);
        order.setStatus(ProductionStatusEnum.EN_ATTENTE);
        return mapper.toDto(productionOrderRepository.save(order));
    }

    @Override
    public ProductionOrderDTO update(Long id, ProductionOrderDTO dto) {
        ProductionOrder order = productionOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
        if (order.getStatus() == ProductionStatusEnum.TERMINE)
            throw new RuntimeException("Impossible de modifier un ordre terminé.");

        order.setStatus(dto.getStatus());
        return mapper.toDto(productionOrderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        ProductionOrder order = productionOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
        if (order.getStatus() != ProductionStatusEnum.EN_ATTENTE)
            throw new RuntimeException("Impossible de supprimer un ordre déjà lancé.");
        productionOrderRepository.delete(order);
    }

    @Override
    public ProductionOrderDTO getById(Long id) {
        return productionOrderRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
    }

    @Override
    public List<ProductionOrderDTO> getAll() {
        return productionOrderRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
