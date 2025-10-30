package org.example.supplychainx.service.approvisionnement.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.approvisionnement.SupplyMaterialDTO;
import org.example.supplychainx.mapper.approvisionnement.SupplyMaterialMapper;
import org.example.supplychainx.model.approvisionnement.SupplyMaterial;
import org.example.supplychainx.repository.approvisionnement.SupplyMaterialRepository;
import org.example.supplychainx.repository.approvisionnement.RawMaterialRepository;
import org.example.supplychainx.service.approvisionnement.SupplyMaterialService;
import org.springframework.stereotype.Service;
import org.example.supplychainx.model.approvisionnement.RawMaterial;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyMaterialServiceImpl implements SupplyMaterialService {

    private final SupplyMaterialRepository supplyMaterialRepository;
    private final SupplyMaterialMapper mapper;
    private final RawMaterialRepository rawMaterialRepository;


    @Override
    public SupplyMaterialDTO create(SupplyMaterialDTO dto) {
        SupplyMaterial supplyMaterial = mapper.toEntity(dto);
        return mapper.toDto(supplyMaterialRepository.save(supplyMaterial));
    }

    @Override
    public SupplyMaterialDTO update(Long id, SupplyMaterialDTO dto) {
        SupplyMaterial supplyMaterial = supplyMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supply Material not found"));

        supplyMaterial.setQuantity(dto.getQuantity());

        if (dto.getMaterialId() != null) {
            RawMaterial material = rawMaterialRepository.findById(dto.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Raw Material not found"));
            supplyMaterial.setMaterial(material);
        }

        return mapper.toDto(supplyMaterialRepository.save(supplyMaterial));
    }

    @Override
    public void delete(Long id) {
        SupplyMaterial supplyMaterial = supplyMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supply Material not found"));

        supplyMaterialRepository.delete(supplyMaterial);
    }

    @Override
    public SupplyMaterialDTO getById(Long id) {
        return supplyMaterialRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Supply Material not found"));
    }

    @Override
    public List<SupplyMaterialDTO> getAll() {
        return supplyMaterialRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
