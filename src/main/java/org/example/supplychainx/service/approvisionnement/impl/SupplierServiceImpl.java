package org.example.supplychainx.service.approvisionnement.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.mapper.approvisionnement.SupplierMapper;
import org.example.supplychainx.model.approvisionnement.RawMaterial;
import org.example.supplychainx.model.approvisionnement.Supplier;
import org.example.supplychainx.model.approvisionnement.SupplyOrderStatusEnum;
import org.example.supplychainx.repository.approvisionnement.RawMaterialRepository;
import org.example.supplychainx.repository.approvisionnement.SupplierRepository;
import org.example.supplychainx.repository.approvisionnement.SupplyOrderRepository;
import org.example.supplychainx.service.approvisionnement.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplyOrderRepository supplyOrderRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierDTO create(SupplierDTO dto) {
        Supplier supplier = supplierMapper.toEntity(dto);

        List<RawMaterial> rawMaterials = new ArrayList<>();
        if (dto.getMaterialIds() != null && !dto.getMaterialIds().isEmpty()) {
            rawMaterials = dto.getMaterialIds().stream()
                    .map(rawMaterialRepository::findByIdMaterial)
                    .toList();
        }

        supplier.setMaterials(rawMaterials);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(savedSupplier);
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Supplier not found"));

        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setLeadTime(dto.getLeadTime());

        List<RawMaterial> rawMaterials = new ArrayList<>();
        if (dto.getMaterialIds() != null && !dto.getMaterialIds().isEmpty()) {
            rawMaterials = dto.getMaterialIds().stream()
                    .map(rawMaterialRepository::findByIdMaterial)
                    .toList();
        }

        supplier.setMaterials(rawMaterials);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(updatedSupplier);
    }


    @Override
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Supplier not found !"));

        boolean hasActiveOrders = supplyOrderRepository.findBySupplierIdSupplier(id)
                .stream()
                .anyMatch(order -> order.getStatus() != SupplyOrderStatusEnum.RECUE);

        if (hasActiveOrders) {
            throw new BusinessException("Impossible de supprimer le fournisseur: il a des commandes actives.");
        }

        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierDTO getById(Long id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDto)
                .orElseThrow(() -> new BusinessException("Supplier not found !!"));
    }

    @Override
    public List<SupplierDTO> getAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .toList();
    }
}
