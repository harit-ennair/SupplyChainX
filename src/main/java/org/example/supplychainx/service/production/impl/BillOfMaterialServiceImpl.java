package org.example.supplychainx.service.production.impl;

import org.example.supplychainx.dto.production.BillOfMaterialDTO;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.mapper.production.BillOfMaterialMapper;
import org.example.supplychainx.model.production.BillOfMaterial;
import org.example.supplychainx.model.production.Product;
import org.example.supplychainx.model.approvisionnement.RawMaterial;
import org.example.supplychainx.repository.production.BillOfMaterialRepository;
import org.example.supplychainx.repository.production.ProductRepository;
import org.example.supplychainx.repository.approvisionnement.RawMaterialRepository;
import org.example.supplychainx.service.production.BillOfMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialServiceImpl implements BillOfMaterialService {

    private final BillOfMaterialRepository billRepository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository materialRepository;
    private final BillOfMaterialMapper mapper;

    @Override
    public BillOfMaterialDTO create(BillOfMaterialDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BusinessException("Produit introuvable (ID=" + dto.getProductId() + ")"));

        RawMaterial material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new BusinessException("Matière introuvable (ID=" + dto.getMaterialId() + ")"));

        BillOfMaterial bom = mapper.toEntity(dto);
        bom.setProduct(product);
        bom.setMaterial(material);

        return mapper.toDto(billRepository.save(bom));
    }

    @Override
    public BillOfMaterialDTO update(Long id, BillOfMaterialDTO dto) {
        BillOfMaterial bom = billRepository.findById(id)
                .orElseThrow(() -> new BusinessException("BOM introuvable"));

        bom.setQuantity(dto.getQuantity());

        if (dto.getMaterialId() != null && !dto.getMaterialId().equals(bom.getMaterial().getIdMaterial())) {
            RawMaterial material = materialRepository.findById(dto.getMaterialId())
                    .orElseThrow(() -> new BusinessException("Nouvelle matière introuvable"));
            bom.setMaterial(material);
        }

        return mapper.toDto(billRepository.save(bom));
    }

    @Override
    public void delete(Long id) {
        BillOfMaterial bom = billRepository.findById(id)
                .orElseThrow(() -> new BusinessException("BOM introuvable !"));

        billRepository.delete(bom);
    }

    @Override
    public BillOfMaterialDTO getById(Long id) {
        return billRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("BOM introuvable !!"));
    }

    @Override
    public List<BillOfMaterialDTO> getAll() {
        return billRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
