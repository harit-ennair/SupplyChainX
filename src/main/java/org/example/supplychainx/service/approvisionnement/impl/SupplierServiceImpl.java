package org.example.supplychainx.service.approvisionnement.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import org.example.supplychainx.mapper.approvisionnement.SupplierMapper;
import org.example.supplychainx.model.approvisionnement.Supplier;
import org.example.supplychainx.model.approvisionnement.SupplyOrderStatusEnum;
import org.example.supplychainx.repository.approvisionnement.SupplierRepository;
import org.example.supplychainx.repository.approvisionnement.SupplyOrderRepository;
import org.example.supplychainx.service.approvisionnement.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplyOrderRepository supplyOrderRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierDTO create(SupplierDTO dto) {
        Supplier supplier = supplierMapper.toEntity(dto);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setRating(dto.getRating());
        supplier.setLeadTime(dto.getLeadTime());
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        boolean hasActiveOrders = supplyOrderRepository.findBySupplierIdSupplier(id)
                .stream()
                .anyMatch(order -> order.getStatus() != SupplyOrderStatusEnum.RECUE);

        if (hasActiveOrders) {
            throw new RuntimeException("Impossible de supprimer le fournisseur: il a des commandes actives.");
        }

        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierDTO getById(Long id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public List<SupplierDTO> getAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .toList();
    }
}
