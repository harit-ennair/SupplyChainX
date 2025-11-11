package org.example.supplychainx.service.approvisionnement.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.approvisionnement.RawMaterialDTO;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.mapper.approvisionnement.RawMaterialMapper;
import org.example.supplychainx.repository.approvisionnement.RawMaterialRepository;
import org.example.supplychainx.repository.approvisionnement.SupplyMaterialRepository;
import org.example.supplychainx.service.approvisionnement.RawMaterialService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;
    private final SupplyMaterialRepository supplyMaterialRepository;
    private final RawMaterialMapper mapper;

    @Override
    public RawMaterialDTO create(RawMaterialDTO dto) {
        return mapper.toDto(
                rawMaterialRepository.save(
                        mapper.toEntity(dto)
                )
        );

    }

    @Override
    public RawMaterialDTO update(Long id, RawMaterialDTO dto) {
        return mapper.toDto(
                rawMaterialRepository.findById(id)
                        .map(existing -> {
                            existing.setName(dto.getName());
                            existing.setStock(dto.getStock());
                            existing.setStockMin(dto.getStockMin());
                            existing.setUnit(dto.getUnit());
                            return rawMaterialRepository.save(existing);
                        })
                        .orElseThrow(() -> new BusinessException("Matière première non trouvée"))
        );
    }

    @Override
    public void delete(Long id) {
        boolean usedInOrders = supplyMaterialRepository.findAll()
                .stream()
                .anyMatch(sm -> sm.getMaterial().getIdMaterial().equals(id));

        if (usedInOrders) {
            throw new BusinessException("Impossible de supprimer la matière : utilisée dans une commande.");
        }

        rawMaterialRepository.deleteById(id);
    }

    @Override
    public RawMaterialDTO getById(Long id) {
        return mapper.toDto(
                rawMaterialRepository.findById(id)
                        .orElseThrow(() -> new BusinessException("Matière première non trouvée !"))
        );
    }

    @Override
    public List<RawMaterialDTO> getAll() {
        return rawMaterialRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
